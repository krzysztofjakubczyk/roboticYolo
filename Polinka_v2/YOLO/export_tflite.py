from pathlib import Path

from ultralytics import YOLO


ROOT_DIR = Path(__file__).resolve().parents[2]
MODEL_PATH = ROOT_DIR / "runs" / "detect" / "train" / "weights" / "best.pt"


def main():
    model = YOLO(str(MODEL_PATH))
    model.export(format="tflite", imgsz=640)


if __name__ == "__main__":
    main()
