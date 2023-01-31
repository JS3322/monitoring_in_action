package com.example.management_data.service;

import com.example.management_data.service.scrap.ScrapService;

import com.example.management_data.entity.ScrapEntityExample;
import com.example.management_data.dto.ScrapDtoExample;
import com.example.management_data.service.Kakaomap_scraping;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class Morphological_Analysis {

    private static ScrapService scrapService;
    private Kakaomap_scraping kakaomap_scraping;

    @Autowired
    public Morphological_Analysis(ScrapService project3Service, Kakaomap_scraping kakaomap_scraping) {
        this.scrapService = project3Service;
        this.kakaomap_scraping = kakaomap_scraping;
    }

    public static ArrayList<Integer> Analysis() {

        //json 파일 위치
        String dir ="cleancode/management_data/SentiWord_info.json";

        ArrayList<Integer> AnalysisArrayList = new ArrayList<>();
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader(dir);
            Object jsonObject = parser.parse(reader);
            JSONArray jsonArray = (JSONArray) jsonObject;

            reader.close();

            ArrayList<HashMap<String, String>> parsedMap = (ArrayList<HashMap<String, String>>) jsonObject;

            //Entity의 값 조회 해서 불러오기
            List<ScrapEntityExample> result_data = project3Service.FindAllProject3db();
            int AnalysisScoreTotal = 0;



            for (ScrapEntityExample result_data1 : result_data) {
                String ViewMoreReviewDetail = result_data1.getViewMoreReviewDetail().strip();
                String title = result_data1.getTitle();
                for (HashMap<String, String> item : parsedMap) {

                   //리뷰 더보기 내용을 가져와서 공백으로 나누고 다시 list에 저장
                    String[] ArrayStr=ViewMoreReviewDetail.split(" ");
                    for(String str : ArrayStr) {
                        System.out.println(str);

                        //리뷰 더보기 내용과 같다면
                        if (item.get("word").equals(str)) {
                            //map을 array로 변환
                            Object[] keys = item.keySet().toArray();
                            //word_root  값
                            String r_word=item.get(keys[0]);
                            //porality 값
                            String s_word=item.get(keys[2]);
                            System.out.println("-------------------------");
                            System.out.println(r_word);
                            System.out.println(s_word);
                            System.out.println("----------------------------------------");
                            System.out.println("word가 같음");
                            System.out.println("----------------------------------------");

                            int AnalysisScore = Integer.parseInt(s_word);
                            if(s_word==null || s_word=="Unkwon"){
                                AnalysisScore=0;
                            }
                            System.out.println("----------------------------------------");
                            System.out.println("word 점수는 :" + AnalysisScore);
                            System.out.println("----------------------------------------");
                            AnalysisScoreTotal +=AnalysisScore;

                            System.out.println("------------------------");
                            System.out.println("음식점명 :" +title);
                            System.out.println("word 총 점수 :"+AnalysisScoreTotal);
                            System.out.println("------------------------");
                        }
                    }
                }
                //평점 가중치
                if (result_data1.getNumberScore() > 5) {
                    AnalysisScoreTotal+=4;
                }else if(result_data1.getNumberScore() > 4.5){
                    AnalysisScoreTotal+=3;
                }else if (result_data1.getNumberScore() > 4){
                    AnalysisScoreTotal+=2;
                }else if (result_data1.getNumberScore() > 3.5){
                    AnalysisScoreTotal+=1;
                }

                //리뷰수
                if(result_data1.getNumberOfReview()>20){
                    AnalysisScoreTotal+=2;
                }

                AnalysisArrayList.add((AnalysisScoreTotal));
                AnalysisScoreTotal=0;

            }
            System.out.println("----------------------------------------");
            System.out.println("----------------------------------------");
            System.out.println("----------------------------------------");
            System.out.println("----------------------------------------");
            System.out.println("----------------------------------------");
            System.out.println("----------------------------------------");
            System.out.println(AnalysisScoreTotal);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return AnalysisArrayList;
    }
    //제목
    public static ArrayList<String> AnalysisTitle(){
        ArrayList<String>AnalysisTitleList = new ArrayList<>();
        List<ScrapEntityExample> result_data = ScrapService.FindAllProject3db();
        for (ScrapEntityExample result_data1 : result_data) {
            result_data1.getTitle();
            AnalysisTitleList.add(result_data1.getTitle());
        }
        return AnalysisTitleList;
    }
    //평점
    public static ArrayList<Double> AnalysisNumberScore(){
        ArrayList<Double>AnalysisNumberScoreList = new ArrayList<>();
        List<ScrapEntityExample> result_data = ScrapService.FindAllProject3db();
        for (ScrapEntityExample result_data1 : result_data) {
            result_data1.getNumberScore();
            AnalysisNumberScoreList.add(result_data1.getNumberScore());
        }
        return AnalysisNumberScoreList;
    }
    //리뷰수 분석+project3 entity
    public static ArrayList<ScrapDtoExample> AnalysisProject3(){
        ArrayList<String>AnalysisProject3List = new ArrayList<>();
        List<ScrapEntityExample> result_data = ScrapService.FindAllProject3db();
        ArrayList<Integer> result_data_index_ver = Analysis();
        ArrayList<ScrapDtoExample> result_calc = new ArrayList<>();
        for(int i = 0; i<result_data.size(); i++) {
            ScrapDtoExample new_dto = new ScrapDtoExample(
                    result_data.get(i).getId(),
                    result_data.get(i).getNumber(),
                    result_data.get(i).getTitle(),
                    result_data.get(i).getNumberScore(),
                    result_data.get(i).getNumberScoreList(),
                    result_data.get(i).getNumberOfReview(),
                    result_data_index_ver.get(i)
                    );
            result_calc.add(new_dto);
        }

        return result_calc;
    }
    //리뷰수
    public static ArrayList<Integer> AnalysisNumberOfReview(){
        ArrayList<Integer>AnalysisNumberOfReviewList = new ArrayList<>();
        List<ScrapEntityExample> result_data = ScrapService.FindAllProject3db();
        for (ScrapEntityExample result_data1 : result_data) {
            result_data1.getNumberOfReview();
            AnalysisNumberOfReviewList.add(result_data1.getNumberOfReview());
        }
        return AnalysisNumberOfReviewList;
    }

    //후기 수
    public static ArrayList<Integer> AnalysisnumberOfScoreList(){
        ArrayList<Integer>numberOfScoreListList = new ArrayList<>();
        List<ScrapEntityExample> result_data = ScrapService.FindAllProject3db();
        for (ScrapEntityExample result_data1 : result_data) {
            result_data1.getNumberScoreList();
            numberOfScoreListList.add(result_data1.getNumberScoreList());
        }
        return numberOfScoreListList;
    }
}
