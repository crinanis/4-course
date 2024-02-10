#-------------------------------------------------------------------------------
# Name:        lab5
# Purpose:
#
# Author:      Ksenya
#
# Created:     24.10.2023
# Copyright:   (c) Ksenya 2023
# Licence:     <your licence>
#-------------------------------------------------------------------------------

import cv2
import numpy as np


# 1. Детекция углов с помощью детектора углов Харриса
angle_image = cv2.imread("angle.jpg")
gray = cv2.cvtColor(angle_image, cv2.COLOR_BGR2GRAY)
gray = np.float32(gray)
normalized = cv2.normalize(gray, None, 0.0, 1.0, cv2.NORM_MINMAX)
corners = cv2.cornerHarris(normalized, blockSize=2, ksize=3, k=0.04) #ядро собеля для вычисления градиентов, чувствительность к угловым точкам
corners = cv2.dilate(corners, None)
# Маркируем угловые точки
angle_image[corners > 0.01 * corners.max()] = [0, 0, 255]  # Красный цвет
cv2.imshow('Edges with Harris detector', angle_image)
cv2.waitKey(0)
cv2.destroyAllWindows()

# 2. Детекция углов с помощью детектора углов Ши Томаси
angle_image = cv2.imread("angle.jpg")
gray = cv2.cvtColor(angle_image, cv2.COLOR_BGR2GRAY)
corners = cv2.goodFeaturesToTrack(gray, maxCorners=100, qualityLevel=0.01, minDistance=10) #хорошие точки

corners = np.int0(corners)

for corner in corners:
    x, y = corner.ravel()
    cv2.circle(angle_image, (x, y), 3, 255, -1)
cv2.imshow('Edges with Shi-Tomasi detector', angle_image)
cv2.waitKey(0)
cv2.destroyAllWindows()

# 3. Применение аффинных преобразований для поворота изображения
file_path = "listik.jpg"
image = cv2.imread(file_path)
image_with_list_contour = image.copy()
image_gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

# Пороги для детекции границ
threshold1 = 180
threshold2 = 330
edges = cv2.Canny(image_gray, threshold1, threshold2)

contours = cv2.findContours(edges.copy(), cv2.RETR_LIST, cv2.CHAIN_APPROX_SIMPLE)[0]
contours = sorted(contours, key=cv2.contourArea, reverse=True)[:5]
screen_contour = None

# Ищем прямоугольник
for contour in contours:
    peri = cv2.arcLength(contour, True)
    approx_epsilon = 0.04
    approx = cv2.approxPolyDP(contour, approx_epsilon * peri, True)
    if len(approx) == 4:
        screen_contour = approx
        break

if screen_contour is not None:
    rect = np.array(screen_contour, dtype="float32")

    dst = np.array([[500, 0], [500, 500], [0, 500], [0, 0]], dtype="float32")

    M = cv2.getPerspectiveTransform(rect, dst)
    image_warped = cv2.warpPerspective(image, M, (500, 500))

    image_warped = cv2.flip(image_warped, 1)
    cv2.imshow('Warped Image', image_warped)
    cv2.waitKey(0)
    cv2.destroyAllWindows()



