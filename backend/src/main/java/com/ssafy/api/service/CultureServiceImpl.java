package com.ssafy.api.service;

import com.ssafy.api.request.CulturePostReq;
import com.ssafy.api.response.CultureListRes;
import com.ssafy.db.entity.Culture;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.CultureRepository;
import com.ssafy.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("cultureService")
public class CultureServiceImpl implements CultureService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    CultureRepository cultureRepository;


    @Override
    public List<CultureListRes> getCultureInfo(String email){
        User user = userRepository.findByEmail(email).get();

        List<Culture> list = user.getCultures();

        List<CultureListRes> resList = new ArrayList<>();

        for(Culture culture:list){
            resList.add(CultureListRes.create(culture));
        }

        return resList;
    }

    @Override
    public void postCulture(CulturePostReq culturePostReq, String email) {
        User user = userRepository.findByEmail(email).get();
        Long userId = user.getUserId();

        Culture culture = new Culture();
        culture.setCultureCategory(culturePostReq.getCultureCategory());
        culture.setCultureTitle(culturePostReq.getCultureTitle());
        culture.setUser(user);
        List<Culture> userCultures = user.getCultures();
        userCultures.add(culture);

        userRepository.save(user);
    }

    @Override
    public void deleteCulture(String email, String cultureTitle, char cultureCategory){
        User user = userRepository.findByEmail(email).get();
        List<Culture> list = user.getCultures();
        list.remove(cultureRepository.findByCultureTitleAndCultureCategory(cultureTitle, cultureCategory).get());
    }

}
