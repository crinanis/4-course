#-------------------------------------------------------------------------------
# Name:        module1
# Purpose:
#
# Author:      Ksenya
#
# Created:     24.10.2023
# Copyright:   (c) Ksenya 2023
# Licence:     <your licence>
#-------------------------------------------------------------------------------

import cv2


class MyOpenCv:
    def __init__(self):
        self.face_cascade = cv2.CascadeClassifier('haarcascade_frontalface_default.xml')
        self.eye_cascade = cv2.CascadeClassifier('haarcascade_eye.xml')

    def detect_face(self, image):
        gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
        faces = self.face_cascade.detectMultiScale(gray, scaleFactor=1.1, minNeighbors=5, minSize=(150, 150))
        for (x, y, w, h) in faces:
            # face
            cv2.rectangle(image, (x, y), (x + w, y + h), (255, 0, 0), 2)
            roi_gray = gray[y:y + h, x:x + w]
            # eyes
            eyes = self.eye_cascade.detectMultiScale(roi_gray, scaleFactor=1.1, minNeighbors=9, minSize=(30, 30))
            for (ex, ey, ew, eh) in eyes:
                cv2.rectangle(image, (x + ex, y + ey), (x + ex + ew, y + ey + eh), (0, 255, 0), 2)

            if len(eyes) == 0:
                cv2.putText(image, "Closed eyes", (x, y - 30), cv2.FONT_HERSHEY_SIMPLEX, 0.5, (0, 0, 255), 2)
            elif len(eyes) == 2:
                cv2.putText(image, "Open eyes", (x, y - 30), cv2.FONT_HERSHEY_SIMPLEX, 0.5, (0, 255, 0), 2)


        return image

    def show_video(self):
        video_capture = cv2.VideoCapture(0)
        while True:
            _, frame = video_capture.read()
            processed_frame = self.detect_face(frame)
            cv2.imshow('Face Detection', processed_frame)
            if cv2.waitKey(1) & 0xFF == 27:
                break
        video_capture.release()
        cv2.destroyAllWindows()


if __name__ == "__main__":
    cv = MyOpenCv()
    cv.show_video()