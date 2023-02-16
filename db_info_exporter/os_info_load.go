package main

import (
	"log"
	"log/syslog"
	"os"
	"path/filepath"
)

//type Os_info_collection_Interface interface {
//	Method_example()
//}

func main() {
	//basic log data
	logFile := openLogFile()
	defer logFile.Close()
	log.SetOutput(logFile)
	log.Println("LOG_INFO", "start code")
	programName := filepath.Base(os.Args[0])
	sysLog, err := syslog.New(syslog.LOG_INFO|syslog.LOG_LOCAL7, programName)
	log.Println(programName)
	if err != nil {
		log.Fatal(err)
	} else {
		log.SetOutput(sysLog)
	}
	log.Println("LOG_INFO + LOG_LOCAL7: Logging in go")

	sysLog, err = syslog.New(syslog.LOG_MAIL, "Some program")
	if err != nil {
		log.Fatal(err)
	} else {
		log.SetOutput(sysLog)
	}
	log.Println("LOG_MAIL: Logging in go")

	log.Println("This is logging!")
}

func openLogFile() *os.File {
	f, err := os.OpenFile("access.log", os.O_APPEND|os.O_CREATE|os.O_WRONLY, 0644)
	if err != nil {
		log.Fatal(err)
	}
	return f
}
