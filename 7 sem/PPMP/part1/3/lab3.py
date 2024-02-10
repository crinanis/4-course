import cv2
import numpy as np

# Инициализация видеопотока с камеры
cap = cv2.VideoCapture(0)  # индекс камеры

while True:
    ret, frame = cap.read()

    if not ret:
        break

    # Оператор Собеля
    sobel = cv2.Sobel(frame, cv2.CV_64F, 1, 1, ksize=5)
    sobel = np.uint8(np.absolute(sobel))

    # Оператор Лапласа
    laplacian = cv2.Laplacian(frame, cv2.CV_64F)
    laplacian = np.uint8(np.absolute(laplacian))

    # Детектор границ Кэнни
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    edges = cv2.Canny(gray, 100, 200)

    cv2.imshow("Original", frame)
    cv2.imshow("Sobel", sobel)
    cv2.imshow("Laplacian", laplacian)
    cv2.imshow("Canny", edges)

    if cv2.waitKey(1) & 0xFF == ord("q"):
        break

cap.release()
cv2.destroyAllWindows()
