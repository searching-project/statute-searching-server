package com.search.statutesearching.service;

import com.search.statutesearching.dto.reponse.ResponseDto;
import com.search.statutesearching.entity.Law;
import com.search.statutesearching.repository.LawRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class LawService {

    private final LawRepository lawRepository;

    @Transactional
    public ResponseDto<?> search(String keyword){
        List<Law> lawList = lawRepository.search(keyword);
        return ResponseDto.success(lawList);
    }
    @Transactional
    public ResponseDto<?> search2(String keyword) {
        List<Law> lawList = lawRepository.search2(keyword);
        return ResponseDto.success(lawList);
    }
    @Transactional
    public ResponseDto<?> search3(String keyword) {
        keyword = getAnd(keyword);
        List<Law> lawList = lawRepository.search3(keyword);
        return ResponseDto.success(lawList);
    }

    //"검색어1" OR "검색어2"가 포함된 데이터를 조회
    public String getOr(String str){
        return str;
    }

    //"검색어1" AND "검색어2"가 모두 포함된 데이터를 조회
    public String getAnd(String str){
        return "+"+str.replace(" "," +");
    }

    //"검색어1"이 포함된 데이터 중에 "검색어2"가 들어간 것 제외하여 조회
    public String getDifference(String str){
        return str.replace(" ", " -");
    }

    //"검색어%"가 포함된 데이터를 조회 ("%검색어" 조회는 안되는듯?)
    public String getStartWith(String str){
        return str+'*';
    }

    //"검색어1 검색어2"가 포함된 데이터를 조회
    public String getSentence(String str){
        return "\""+str+"\"";
    }
}
