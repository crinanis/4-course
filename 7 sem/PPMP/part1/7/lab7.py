#-------------------------------------------------------------------------------
# Name:        module1
# Purpose:
#
# Author:      Ksenya
#
# Created:     25.10.2023
# Copyright:   (c) Ksenya 2023
# Licence:     <your licence>
#-------------------------------------------------------------------------------

import cv2
import numpy as np

## Background Subtraction Methods
cap = cv2.VideoCapture(0)

mog = cv2.createBackgroundSubtractorMOG2()

while True:
    ret, frame = cap.read()
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)

    fgmask = mog.apply(gray)

    kernel = cv2.getStructuringElement(cv2.MORPH_ELLIPSE, (5, 5))
    fgmask = cv2.erode(fgmask, kernel, iterations=1)
    fgmask = cv2.dilate(fgmask, kernel, iterations=1)

    contours, hierarchy = cv2.findContours(fgmask, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)

    for contour in contours:
    # Ignore small contours
        if cv2.contourArea(contour) < 100:
            continue

     # Draw a circle around the contour
        (x, y), radius = cv2.minEnclosingCircle(contour)
        center = (int(x), int(y))
        radius = int(radius)
        cv2.circle(frame, center, radius, (0, 0, 255), 2)

    cv2.imshow('Motion Detection', frame)
    if cv2.waitKey(1) == ord('q'):
        break

cap.release()
cv2.destroyAllWindows()

##calcOpticalFlowPyrLK ()

cap = cv2.VideoCapture(0)  # Открываем видеопоток с камеры

# Инициализируем оптический поток Лукаса-Канаде
lk_params = dict(winSize=(15, 15), maxLevel=2, criteria=(cv2.TERM_CRITERIA_EPS | cv2.TERM_CRITERIA_COUNT, 10, 0.03))

while True:
    ret, frame = cap.read()
    if not ret:
        break

    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)

    if 'old_gray' not in locals():
        old_gray = gray
        p0 = cv2.goodFeaturesToTrack(old_gray, mask=None, maxCorners=100, qualityLevel=0.3, minDistance=7)
        mask = np.zeros_like(frame)

    p1, st, err = cv2.calcOpticalFlowPyrLK(old_gray, gray, p0, None, **lk_params)

    good_new = p1[st == 1]
    good_old = p0[st == 1]

    for i, (new, old) in enumerate(zip(good_new, good_old)):
        a, b = new.ravel()
        c, d = old.ravel()
        mask = cv2.line(mask, (int(a), int(b)), (int(c), int(d)), (0, 0, 255), 2)
        frame = cv2.circle(frame, (int(a), int(b)), 5, (0, 0, 255), -1)

    img = cv2.add(frame, mask)
    cv2.imshow('Optical Flow', img)

    if cv2.waitKey(1) == ord('q'):
        break

cap.release()
cv2.destroyAllWindows()

##Tracker

cap = cv2.VideoCapture(0)

tracker = cv2.TrackerGOTURN_create()

ret, frame = cap.read()

# Выбираем область для отслеживания
bbox = cv2.selectROI("Select Object", frame)
tracker.init(frame, bbox)

while True:
    ret, frame = cap.read()
    if not ret:
        break

    # Обновляем трекер
    ret, bbox = tracker.update(frame)

    if ret:
        x, y, w, h = [int(i) for i in bbox]
        cv2.rectangle(frame, (x, y), (x + w, y + h), (0, 255, 0), 2)

    cv2.imshow("Tracking", frame)

    if cv2.waitKey(1) == ord('q'):
        break

cap.release()
cv2.destroyAllWindows()


