package com.example.management_data.service;

import com.example.management_data.entity.ScrapEntityExample;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//selenium : 동적인 데이터 수집 가능 (직접 브라우저를 통제해서 사람처럼 브라우저 작동을 하여 데이터 수집) : chromeDriver
//jsoup : httpRequest 사용해서 정적인 데이터(HTML, CSS..)를 수집
@Service
public class Kakaomap_scraping {
//    메인 실행
//    public static void main(String[] args) {
//        scraping();
//    }

    //selenum 드라이버 다운
    private static WebDriver driver;
    private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    //    private static final String WEB_DRIVER_PATH = "/Users/js/Cleancode/lecture_spring_2_crudProject/src/main/resources/static/tool/chromedriver";
    //chromedriver 위치
    static String chromeDir="D:/cleancode/project3_java_20230119/chromedriver.exe";
    private static final String WEB_DRIVER_PATH = chromeDir;


    //메서드 매개변수로 받아서 스크래핑 동작을 위한 변수 선언
    private static String base_url;
    
    public static ArrayList<ScrapEntityExample> scraping() {
        //System.io : 개발한 자바 프로그램(런타임)에서 외부 프로그램을 작동하기 위한 객체
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        driver = new ChromeDriver();

        base_url = "https://map.kakao.com/link/search/수원시청치킨";
//        base_url = "https://map.kakao.com/link/search/수원시청음식점";
        driver.get(base_url);

        ArrayList<ScrapEntityExample> project3EntityArrayList = new ArrayList<>();
        List<WebElement> elements_button;
        List<WebElement> elements_button1;
        List<WebElement> elements_button2;
        List<WebElement> elements_button3;
        List<WebElement> elements_total;
        List<WebElement> elements_totalReview;
        List<WebElement> elements_viewMoreReviewDetail;
        int number = 0;

        try {
            Thread.sleep(10000);

            //스크래핑할 페이지의 전체 데이터 출력
//            System.out.println(driver.getPageSource());
            JavascriptExecutor js = (JavascriptExecutor) driver;
/////////////////////////////////////////////가게 정보 가져오기
            //카카오맵 더 보기 클릭
            WebElement viewMore = driver.findElement(By.id("info.search.place.more"));
            ((ChromeDriver) driver).executeScript("arguments[0].click();", viewMore);
            Thread.sleep(2000);



            //1~5 페이지 넘버 클릭
            for (int z = 1; z < 6; z++) {
                WebElement numPage = driver.findElement(By.id(String.format("info.search.page.no%d", z)));
                //클릭
                ((ChromeDriver) driver).executeScript("arguments[0].click();", numPage);
                Thread.sleep(2000);

                elements_button = driver.findElements(By.className("link_name"));
                elements_button1 = driver.findElements(By.cssSelector("em[data-id='scoreNum']"));
                elements_button2 = driver.findElements(By.cssSelector("a[data-id='numberofscore']"));
                elements_button3 = driver.findElements(By.cssSelector("em[data-id='numberofreview']"));
                elements_total = driver.findElements(By.cssSelector("em[id='info.search.place.cnt']"));
                elements_viewMoreReviewDetail = driver.findElements(By.cssSelector("div.comment_info > p > span"));
                elements_totalReview = driver.findElements(By.cssSelector("#mArticle > div.cont_evaluation > strong > span"));

                for (int i = 0; i < elements_button.size(); i++) {

                    String title = elements_button.get(i).getText();
                    String numberScore = elements_button1.get(i).getText();
                    String numberofscore = elements_button2.get(i).getText();
                    String[] numberOfScoreList = numberofscore.split("건", 5);
                    String numberOfReview = elements_button3.get(i).getText();
                    String total = elements_total.get(0).getText();

                    System.out.println("------------------------------------------");
                    System.out.println("현재 번호 :" + number);
                    System.out.println("음식점명 : " + title);
                    System.out.println("평점 : " + numberScore);
                    System.out.println("평점 리뷰 수 : " + numberOfScoreList[0]);
                    System.out.println("리뷰 수 : " + numberOfReview);
                    System.out.println("------------------------------------------");
                    number += 1;
                    String viewMoreReviewDetail = null;
///////////////////////////////////리뷰 더보기 가져오기
                    //현재 Window 저장
                    String parentHandle = driver.getWindowHandle();

                    //리뷰 클릭
                    elements_button2 = driver.findElements(By.cssSelector("a[data-id='numberofscore']"));
                    ((ChromeDriver) driver).executeScript("arguments[0].click();", elements_button2.get(i));
                    Thread.sleep(2000);

                    //현재 Window 목록 조회
                    Set<String> windowList = driver.getWindowHandles();
                    for (String windowHandle : windowList) {
                        if (parentHandle.equals(windowHandle)) {
                            continue;
                        }
                        //원하는 Window로 이동
                        driver.switchTo().window(windowHandle);
                    }

                    //리뷰 더보기 클릭
                    while(true) {
                        try {
                            WebElement viewMoreReview = driver.findElement(By.cssSelector("#mArticle > div.cont_evaluation > div.evaluation_review > a > span.txt_more"));
                            ((ChromeDriver) driver).executeScript("arguments[0].click();", viewMoreReview);
                            Thread.sleep(2000);
                        }catch (Exception e) {
                            System.out.println("viewMoreReview 없음");
                            break;
                        }
                    }
                    if(Integer.parseInt(numberOfScoreList[0])==0){
                        viewMoreReviewDetail="리뷰없음";
                    }
                    //리뷰 더보기 내용 가져오기
                    if(Integer.parseInt(numberOfScoreList[0])>0){
                            elements_totalReview = driver.findElements(By.cssSelector("#mArticle > div.cont_evaluation > strong > span"));
                            String totalReview = elements_totalReview.get(0).getText();
                            System.out.println("-------------------------");
                            System.out.println("-------------------------");
                            System.out.println("-------------------------");
                            System.out.println(totalReview);

                    for (int j = 0; j < Integer.parseInt(totalReview); j++) {
                        elements_viewMoreReviewDetail = driver.findElements(By.cssSelector("div.comment_info > p > span"));
                        viewMoreReviewDetail = elements_viewMoreReviewDetail.get(j).getText();
                        System.out.println("-------------------------");
                        System.out.println("리뷰 더보기 내용 : " + viewMoreReviewDetail);
                        System.out.println("-------------------------");
                        if(viewMoreReviewDetail==null){
                            viewMoreReviewDetail="리뷰내용없음"+j;
                        }
                    }
                    }

///////////////////////////////////리뷰 더보기 가져오기 끝
                    //scraping 데이터 list에 저장
                    ScrapEntityExample project3Entity = new ScrapEntityExample(
                            number, title, Double.parseDouble(numberScore),
                            Integer.parseInt(numberOfScoreList[0]), Integer.parseInt(numberOfReview),
                            viewMoreReviewDetail
                    );

                    project3EntityArrayList.add(project3Entity);

                    //탭 닫기
                    driver.close();

                    //기존 Window로 이동
                    driver.switchTo().window(parentHandle);
                    Thread.sleep(2000);
                    System.out.println("----------------------");
                    System.out.println("----------------------");
                    System.out.println("----------------------");
                    System.out.println("----------------------");
                    System.out.println(number);
                    System.out.println(total);
                    System.out.println("----------------------");
                    System.out.println("----------------------");
                    System.out.println("----------------------");
                    System.out.println("----------------------");

                    if (number == Integer.parseInt(total)) {
                    try {
                        System.out.println(" TESTESTTEST");
                        WebElement nextPage = driver.findElement(By.id("info.search.page.next1"));
                        //클릭
                        ((ChromeDriver) driver).executeScript("arguments[0].click();", nextPage);
                    }catch (Exception e) {
                        System.out.println("마지막페이지가 없습니다");
                        throw new Exception("마지막 페이지까지 조회 완료 입니다");
                    }
                }
                }
                //다음 페이지 클릭
                if (z % 5 == 0) {
                    WebElement nextPage = driver.findElement(By.id("info.search.page.next"));
                    //클릭
                    ((ChromeDriver) driver).executeScript("arguments[0].click();", nextPage);
                    Thread.sleep(2000);
                    z = 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //공식문서에서는 close()가 아니라 quit() 권장
            driver.quit();
        }
        return project3EntityArrayList;
    }



}
