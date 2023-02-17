package ru.levelup.at.homework2;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("ru.levelup.at.homework2")
@IncludeTags({"Positive"})

public class PositiveTestSuite {
    //Класс, содержащий набор позитивных тестовых сценариев метода isLucky класс LuckyTicket
}
