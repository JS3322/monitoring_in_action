package main

import (
	"fmt"
	"os"
	"syscall"
)

//func main()  {
//	test()
//}

func test() {
	var mem syscall.
	err := syscall.Sysinfo(&mem)
	if err != nil {
		fmt.Println(err)
		os.Exit(1)
	}
	fmt.Println("Total Memory: ", mem.Totalram)
	fmt.Println("Free Memory: ", mem.Freeram)
	fmt.Println("Number of CPUs: ", mem.Procs)
}
