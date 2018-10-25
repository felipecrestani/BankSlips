package com.bankslipsrest;

import com.bankslipsrest.controller.BankSlipsControllerTests;
import com.bankslipsrest.entity.BankSlipsFineTest;
import com.bankslipsrest.service.BankSlipsServiceTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
    DemoApplicationTests.class,
    BankSlipsServiceTests.class, 
    BankSlipsControllerTests.class,
    BankSlipsFineTest.class
})
public class SuiteTest {

}