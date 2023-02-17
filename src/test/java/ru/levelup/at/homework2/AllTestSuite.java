package ru.levelup.at.homework2;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("ru.levelup.at.homework2")
@IncludeTags({"Positive", "Negative"})
public class AllTestSuite {
    //Класс, содержащий набор позитивных и негативных тестовых сценариев метода isLucky класс LuckyTicket
}
