package main

import (
	"fmt"
	"os"
	"os/exec"
	"strings"
	"time"
)

// os를 확인
func check_os() string {
	result := "mac"
	return result
}
func main() {

	var folder_name_list []string
	targetDir := "/Users/js/Cleancode"
	//targetDir := "/home/ubuntu/cleancode/"
	files, err := os.ReadDir(targetDir)
	if err != nil {
		fmt.Println(err)
	}
	//cleancode 안에 있는 폴더 이름 모두 조회
	for _, file := range files {
		// 파일명
		fmt.Println(file.Name())
		if strings.IndexAny(file.Name(), "_") != 0 && strings.IndexAny(file.Name(), ".") != 0 {
			folder_name_list = append(folder_name_list, file.Name())
		}

		// 파일의 절대경로
		fmt.Println(fmt.Sprintf("%v/%v", targetDir, file.Name()))
	}

	for _, folder_name := range folder_name_list {
		fmt.Println("---------------------" + folder_name)
		cmd := exec.Command("git", "pull")
		cmd.Dir = "/Users/js/Cleancode/" + folder_name
		output, err := cmd.Output()
		if err != nil {
			fmt.Println(err)
			time.Sleep(5 * time.Second)
		} else {
			fmt.Println(string(output))
		}
	}

	//fmt.Println(folder_name_list)

}
