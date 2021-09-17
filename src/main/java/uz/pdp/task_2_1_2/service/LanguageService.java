package uz.pdp.task_2_1_2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task_2_1_2.entity.Language;
import uz.pdp.task_2_1_2.payload.ApiResponse;
import uz.pdp.task_2_1_2.repository.LanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    @Autowired
    LanguageRepository languageRepository;

    public ApiResponse addLanguage(Language languageDto){
        boolean b = languageRepository.existsByName(languageDto.getName());
        if (b){
            return new ApiResponse("laguage already exists",false);
        }
        languageRepository.save(languageDto);
        return new ApiResponse("language added",true);
    }
//    get all language
    public List<Language> getLanguage(){
        List<Language> all = languageRepository.findAll();
        return all;
    }

//    get language by id
    public Language getLanguageById(Integer id){
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (!optionalLanguage.isPresent()){
            return null;
        }
        return optionalLanguage.get();
    }
//    edit language
    public ApiResponse editLanguage(Integer id,Language languageDto){
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (!optionalLanguage.isPresent()){
            return new ApiResponse("language not found",false);
        }
        boolean b = languageRepository.existsByNameAndIdNot(languageDto.getName(), id);
        if (b) {
            return new ApiResponse("language already exists",false);
        }
        Language language = optionalLanguage.get();
        language.setName(languageDto.getName());
        languageRepository.save(language);
        return new ApiResponse("language edited",true);
    }


    public ApiResponse deleteLanguage(Integer id){
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (optionalLanguage.isPresent()){
            languageRepository.deleteById(id);
            return new ApiResponse("language deleted",true);
        }
        return new ApiResponse("language not found",false);
    }
}
