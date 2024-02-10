#-------------------------------------------------------------------------------
# Name:        lab
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
import matplotlib.pyplot as plt

def show_image(title, image):
    cv2.imshow(title, image)
    cv2.waitKey(0)
    cv2.destroyAllWindows()

normal_image = cv2.imread('normal.bmp')

gray_image = cv2.cvtColor(normal_image, cv2.COLOR_BGR2GRAY)
cv2.imwrite('gray.bmp', gray_image)

threshold_value = 128
_, thresholded_image = cv2.threshold(gray_image, threshold_value, 255, cv2.THRESH_BINARY)
cv2.imwrite('thresholed.bmp', thresholded_image)

adaptive_thresholded_image = cv2.adaptiveThreshold(gray_image, 255, cv2.ADAPTIVE_THRESH_MEAN_C, cv2.THRESH_BINARY, 11, 2)
cv2.imwrite('adaptive_thresholded.bmp', adaptive_thresholded_image)

show_image("Input Image", normal_image)
show_image("Gray Image", gray_image)
show_image("Thresholded Image", thresholded_image)
show_image("Adaptive Thresholded Image", adaptive_thresholded_image)

# С затемнённым изображением
dark_image = cv2.imread('dark.bmp')
input_image_histogram = cv2.calcHist([dark_image], [0], None, [256], [0, 256])

# Примените выравнивание гистограммы к каждому каналу цвета
equalized_image = cv2.cvtColor(dark_image, cv2.COLOR_BGR2YCrCb)
equalized_image[:, :, 0] = cv2.equalizeHist(equalized_image[:, :, 0])
equalized_image = cv2.cvtColor(equalized_image, cv2.COLOR_YCrCb2BGR)

cv2.imwrite('equalized_image.bmp', equalized_image)

equalized_image_histogram = cv2.calcHist([equalized_image], [0], None, [256], [0, 256])

show_image("Equalized Image", equalized_image)

plt.plot(input_image_histogram)
plt.xlim([0, 256])
plt.title("Input Image Histogram")
plt.show()

plt.plot(equalized_image_histogram)
plt.xlim([0, 256])
plt.title("Equelized Image Histogram")
plt.show()
