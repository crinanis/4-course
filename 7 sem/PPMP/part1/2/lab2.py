#-------------------------------------------------------------------------------
# Name:        lab2
# Purpose:
#
# Author:      Ksenya
#
# Created:     12.10.2023
# Copyright:   (c) Ksenya 2023
# Licence:     <your licence>
#-------------------------------------------------------------------------------

import cv2
import numpy as np

def show_image(title, image):
    cv2.imshow(title, image)
    cv2.waitKey(0)
    cv2.destroyAllWindows()

image = cv2.imread('normal.bmp')
show_image('Original Image', image)

# 1. Свертка изображения линейными фильтрами
# фильтр увеличения контрастности
kernel = np.array([[0, -1, 0], [-1, 5, -1], [0, -1, 0]], dtype=np.float32) #создание пользовательского ядра (фильтра)
linear = cv2.filter2D(image, -1, kernel)
show_image('Linear Filter', linear)

# 2. Сглаживание изображения
blurred = cv2.blur(image, (5, 5))
show_image('Blurred Image', blurred)

gaussian_blurred = cv2.GaussianBlur(image, (5, 5), 0)
show_image('Gaussian Blurred Image', gaussian_blurred)

median_blurred = cv2.medianBlur(image, 5)
show_image('Median Blurred Image', median_blurred)

# 3. Эрозия и дилатация
_, binary_image = cv2.threshold(image, 128, 255, cv2.THRESH_BINARY)

kernel = np.ones((5, 5), np.uint8)
eroded_image = cv2.erode(binary_image, kernel, iterations=1)
dilated_image = cv2.dilate(binary_image, kernel, iterations=1)

show_image('Eroded Threshold Image', eroded_image)
show_image('Dilated Threshold Image', dilated_image)

cv2.imwrite('ErodedThresholdImage.bmp', eroded_image)
cv2.imwrite('DilatedThresholdImage.bmp', dilated_image)

# 4. Адаптивная бинаризация для каждого канала
b, g, r = cv2.split(image)
adaptive_binary_b = cv2.adaptiveThreshold(b, 255, cv2.ADAPTIVE_THRESH_MEAN_C, cv2.THRESH_BINARY, 11, 2)
adaptive_binary_g = cv2.adaptiveThreshold(g, 255, cv2.ADAPTIVE_THRESH_MEAN_C, cv2.THRESH_BINARY, 11, 2)
adaptive_binary_r = cv2.adaptiveThreshold(r, 255, cv2.ADAPTIVE_THRESH_MEAN_C, cv2.THRESH_BINARY, 11, 2)

# Объединяем каналы в одно цветное изображение
adaptive_binary_image = cv2.merge((adaptive_binary_b, adaptive_binary_g, adaptive_binary_r))

kernel = np.ones((5, 5), np.uint8)
eroded_image = cv2.erode(adaptive_binary_image, kernel, iterations=1)
dilated_image = cv2.dilate(adaptive_binary_image, kernel, iterations=1)

show_image('Eroded Adaptive Threshold Image', eroded_image)
show_image('Dilated Adaptive Threshold Image', dilated_image)

cv2.imwrite('ErodedAdaptiveThresholdImage.bmp', eroded_image)
cv2.imwrite('DilatedAdaptiveThresholdImage.bmp', dilated_image)
cv2.imwrite('AdaptiveThresholdImage.bmp', adaptive_binary_image)


cv2.waitKey(0)
cv2.destroyAllWindows()
