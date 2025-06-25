package org.example;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@ToString
@EqualsAndHashCode
public class Student {

    @Getter    @Setter
    private String name;
    private List<Integer> grades = new ArrayList<>();

    private final GrageChecker checker;

    public Student(String name, GrageChecker checker) {
        this.name = name;
        this.checker = checker;
    }

    public List<Integer> getGrades() {
        return new ArrayList<>(grades);
    }

//    @SneakyThrows
//    public void addGrade(int grade) {
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpGet request = new HttpGet("http://localhost:5352/checkGrade?grade="+grade);
//        CloseableHttpResponse httpResponse = httpClient.execute(request);
//        HttpEntity entity = httpResponse.getEntity();
//        if(!Boolean.parseBoolean(EntityUtils.toString(entity))){
//            throw new IllegalArgumentException(grade + " is wrong grade");
//        }
//        grades.add(grade);
//    }

//    @SneakyThrows
//    public int raiting() {
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpGet request = new HttpGet("http://localhost:5352/educ?sum="+grades.stream().mapToInt(x->x).sum());
//        CloseableHttpResponse httpResponse = httpClient.execute(request);
//        HttpEntity entity = httpResponse.getEntity();
//        return Integer.parseInt(EntityUtils.toString(entity));
//    }

    //Student не знает о HttpClient —> его можно тестировать,подставляя нужную реализацию GradeChecker
    public void addGrade(int grade) {
        if (!checker.isValid(grade)) {
            throw new IllegalArgumentException(grade + " is wrong grade");
        }
        grades.add(grade);
    }
}


