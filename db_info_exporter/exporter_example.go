package main

import (
	"flag"
	"fmt"
	"os"
)

func main() {

	//프로그램 실행 인자값
	args := os.Args
	fmt.Println(args)
	//첫번째 인자는 프로그램 이름
	args_1 := args[0]
	fmt.Print(args_1)

	restArgs := args[1:]
	fmt.Println(restArgs)

	for idx, arg := range restArgs {
		fmt.Printf("%d번째 인자: %s\n", idx, arg)
	}

	/*
		GET OS Parameter
	*/
	var bind string
	flag.StringVar(&bind, "bind", "0.0.0.0:9104", "bind")
	flag.Parse()
}
