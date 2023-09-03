import tkinter as tk
from tkinter import ttk
import threading


def on_button_click(video_path):
    print(video_path)

def main():
    root = tk.Tk()
    root.title('Video Player')
    root.geometry('400x300')

    button1 = ttk.Button(root, text='Video 1', command=lambda: on_button_click('video_example.mp4'))
    button1.grid(row=0, column=0, padx=10, pady=10)

    button2 = ttk.Button(root, text='Video 2', command=lambda: on_button_click('video_example.mp4'))
    button2.grid(row=0, column=1, padx=10, pady=10)

    button3 = ttk.Button(root, text='Video 3', command=lambda: on_button_click('video_example.mp4'))
    button3.grid(row=1, column=0, padx=10, pady=10)

    button4 = ttk.Button(root, text='Video 4', command=lambda: on_button_click('video_example.mp4'))
    button4.grid(row=1, column=1, padx=10, pady=10)

    root.mainloop()

if __name__ == '__main__':
    main()
