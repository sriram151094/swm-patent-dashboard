package com.swm.dashboard.web;


import com.swm.dashboard.data.entity.PatentKeywords;
import com.swm.dashboard.data.repository.PatentKeywordsRepository;
import com.swm.dashboard.service.StringUtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PatentKeywordsWebController {

    PatentKeywordsRepository patentKeywordsRepository;

    @Autowired
    public PatentKeywordsWebController(PatentKeywordsRepository patentKeywordsRepository) {
        this.patentKeywordsRepository = patentKeywordsRepository;
    }

    //get all keywords corresponding to a patent
    @CrossOrigin
    @GetMapping("api/patent_keywords/")
    public PatentKeywords getAllPatentKeywordsByPatentId(@RequestParam(name = "patent_id", required = true) String patentId) {
        //to-do add patent id validation from main table
        String patent = StringUtilsService.getFormattedString(patentId);
        PatentKeywords patentKeywords = this.patentKeywordsRepository.findPatentKeywordsByPatentId(patent);
        return patentKeywords;
    }

    //get all patent keyword combinations
    @CrossOrigin
    @GetMapping("api/patent_keywords")
    public List<PatentKeywords> getAllPatentKeywords(Model model) {
        Iterable<PatentKeywords> patentKeywords = this.patentKeywordsRepository.findAll();
        List<PatentKeywords> resultPatentKeywordsList = new ArrayList<>();

        for (PatentKeywords patentKeyword : patentKeywords) {
            resultPatentKeywordsList.add(patentKeyword);
                break;
        }
        return resultPatentKeywordsList;
    }
}
