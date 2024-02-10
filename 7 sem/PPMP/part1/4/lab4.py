#-------------------------------------------------------------------------------
# Name:        lab4
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

image = cv2.imread("2.bmp")
img_gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
ret, thresh = cv2.threshold(img_gray, 190, 255, cv2.THRESH_BINARY)

#### обнаруживаем все контуры на двоичном изображении
contours, hierarchy = cv2.findContours(image=thresh, mode=cv2.RETR_TREE, method=cv2.CHAIN_APPROX_SIMPLE)

max_area = max(contours, key=cv2.contourArea)
filtered_contours = [contour for contour in contours if contour is not max_area]

# рисуем контуры на исходном изображении
image_copy = image.copy()
cv2.drawContours(image=image_copy, contours=filtered_contours, contourIdx=-1, color=(0, 255, 0), thickness=2, lineType=cv2.LINE_AA)

cv2.imshow('All contours', image_copy)
cv2.waitKey(0)
cv2.destroyAllWindows()
cv2.imwrite('all_contours.jpg', image_copy)

image_copy = image.copy()
#### Нарисовать прямоугольники, в которые вписаны предметы
bounding_boxes = [cv2.boundingRect(contour) for contour in filtered_contours]
for x, y, w, h in bounding_boxes:
    cv2.rectangle(image_copy, (x, y), (x + w, y + h), (0, 0, 255), 2)

#### Вывести количество предметов
num_items = len(bounding_boxes)
cv2.putText(image_copy, f'Items: {num_items}', (10, 30), cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 0, 255), 2)

cv2.imshow('all items', image_copy)
cv2.waitKey(0)
cv2.destroyAllWindows()
cv2.imwrite('all_items.jpg', image_copy)

#### Найти контур с наибольшей длиной
max_length_contour = max(filtered_contours, key=lambda x: cv2.arcLength(x, closed=True))

#### Найти контур с наибольшей площадью
max_area_contour = max(filtered_contours, key=cv2.contourArea)

image_copy_length = image.copy()
image_copy_area = image.copy()

# Выделить контур с наибольшей длиной
cv2.drawContours(image_copy_length, [max_length_contour], -1, (0, 0, 255), 2)

# Выделить контур с наибольшей площадью
cv2.drawContours(image_copy_area, [max_area_contour], -1, (0, 0, 255), 2)

cv2.imshow('Max Length Contour', image_copy_length)
cv2.waitKey(0)
cv2.destroyAllWindows()

cv2.imshow('Max Area Contour', image_copy_area)
cv2.waitKey(0)
cv2.destroyAllWindows()

image = cv2.imread("2.bmp")
img_gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
ret, thresh = cv2.threshold(img_gray, 190, 255, cv2.THRESH_BINARY)
contours, hierarchy = cv2.findContours(image=thresh, mode=cv2.RETR_TREE, method=cv2.CHAIN_APPROX_SIMPLE)

max_area = max(contours, key=cv2.contourArea)
filtered_contours = [contour for contour in contours if contour is not max_area]

image_copy = image.copy()

quadrilaterals = 0
for contour in filtered_contours:
    epsilon = 0.099 * cv2.arcLength(contour, True) # значение для аппроксимации контура, true - замкнутый
    approx = cv2.approxPolyDP(contour, epsilon, True) # сохраняет только значения угловых значимых точек

    if len(approx) == 4:
        cv2.drawContours(image_copy, [contour], -1, (0, 255, 0), 2, cv2.LINE_AA)
        quadrilaterals += 1

cv2.imshow('All rectangles', image_copy)
cv2.waitKey(0)
cv2.destroyAllWindows()
print(f"Количество четырёхугольных фигур: {quadrilaterals}")

### Задание 3.

image = cv2.imread("2.bmp", cv2.IMREAD_COLOR)
image_gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

edges = cv2.Canny(image_gray, 50, 200, None, 3) #детектор границ

lines = cv2.HoughLines(edges, 1, np.pi / 180, 100, None, 0, 0) #пороговое значение для голосования

if lines is not None:
    for line in lines:
        rho, theta = line[0]
        a = np.cos(theta)
        b = np.sin(theta)
        x0 = a * rho
        y0 = b * rho
        pt1 = (int(x0 + 1000 * (-b)), int(y0 + 1000 * (a)))
        pt2 = (int(x0 - 1000 * (-b)), int(y0 - 1000 * (a)))

        cv2.line(image, pt1, pt2, (0,255,0), 3, cv2.LINE_AA)

cv2.imshow('image with HoughLines', image)
cv2.waitKey(0)
cv2.destroyAllWindows()
cv2.imwrite('houghlines.jpg', image)


image = cv2.imread("4.bmp")
image = cv2.medianBlur(image,5)
image_gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
thresh = cv2.threshold(image_gray, 0, 255, cv2.THRESH_BINARY_INV + cv2.THRESH_OTSU)[1]

circles = cv2.HoughCircles(thresh, cv2.HOUGH_GRADIENT, dp=2, minDist=100, param1=30, param2=30, minRadius=20, maxRadius=60) #dp-кратность разрешения, мин расстояние между центр, пороги

circles = np.uint16(np.around(circles))
num_circles = len(circles[0])

for i in circles[0, :]:
    cv2.circle(image, (i[0], i[1]), i[2], (0, 255, 0), 2) # center
    cv2.circle(image, (i[0], i[1]), 2, (0, 0, 255), 3) # outer

print(f"Количество найденных кругов: {num_circles}")
cv2.imshow('Circles', image)
cv2.waitKey(0)
cv2.destroyAllWindows()
cv2.imwrite('circles.jpg', image)