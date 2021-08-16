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
            resList.add(CultureListRes.create(culture, email));
        }

        return resList;
    }

    @Override
    public void postCulture(CulturePostReq culturePostReq, String email) {
        User user = userRepository.findByEmail(email).get();
        //System.out.println(user);

        List<Culture> userCultures = user.getCultures();

        Culture culture = new Culture();
        culture.setUser(user);
        culture.setCultureCategory(culturePostReq.getCultureCategory());
        culture.setCultureTitle(culturePostReq.getCultureTitle());

        userCultures.add(culture);
        //System.out.println(culture.getCultureCategory());
        userRepository.save(user);

    }

    @Override
    public void deleteCulture(String myEmail, String cultureTitle, int cultureCategory){
        User user = userRepository.findByEmail(myEmail).get();
        List<Culture> list = user.getCultures();
        Culture culture = cultureRepository.findByCultureTitleAndCultureCategory(cultureTitle, cultureCategory).get();
        list.remove(culture);

        cultureRepository.delete(culture);
        userRepository.save(user);
    }

}
