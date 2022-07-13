package ru.netology.aqa0bdd.test;

import org.junit.jupiter.api.Test;
import ru.netology.aqa0bdd.data.DataHelper;
import ru.netology.aqa0bdd.page.LoginPageV1;
import ru.netology.aqa0bdd.page.LoginPageV2;
import ru.netology.aqa0bdd.page.LoginPageV3;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {
    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
      open("http://localhost:9999");
      var loginPage = new LoginPageV1();
//    var loginPage = open("http://localhost:9999", LoginPageV1.class);
      var authInfo = DataHelper.getAuthInfo();
      var verificationPage = loginPage.validLogin(authInfo);
      var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
      var dashboardPage = verificationPage.validVerify(verificationCode);
      var card1 = DataHelper.getCardInfo1();
      var card2 = DataHelper.getCardInfo2();
      var card1Balance = dashboardPage.getCardBalance(card1);
      var card2Balance = dashboardPage.getCardBalance(card2);
      var cardPage = dashboardPage.validSelectReceiveCard(card1);
      int transferAmount = 200;
      dashboardPage = cardPage.validTransfer(card2, transferAmount);
      card1Balance += transferAmount;
      card2Balance -= transferAmount;
      var exactCard2Balance = dashboardPage.getCardBalance(card2);
      var exactCard1Balance = dashboardPage.getCardBalance(card1);
      assertEquals(card1Balance, exactCard1Balance);
      assertEquals(card2Balance, exactCard2Balance);

    }

  @Test
  void shouldTransferMoneyBetweenOwnCardsV2() {
    open("http://localhost:9999");
    var loginPage = new LoginPageV2();
//    var loginPage = open("http://localhost:9999", LoginPageV2.class);
    var authInfo = DataHelper.getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    var dashboardPage = verificationPage.validVerify(verificationCode);
    var card1 = DataHelper.getCardInfo1();
    var card2 = DataHelper.getCardInfo2();
    var card1Balance = dashboardPage.getCardBalance(card1);
    var card2Balance = dashboardPage.getCardBalance(card2);
    var cardPage = dashboardPage.validSelectReceiveCard(card2);
    int transferAmount = 100;
    dashboardPage = cardPage.validTransfer(card1, transferAmount);
    card1Balance -= transferAmount;
    card2Balance += transferAmount;
    var exactCard2Balance = dashboardPage.getCardBalance(card2);
    var exactCard1Balance = dashboardPage.getCardBalance(card1);
    assertEquals(card1Balance, exactCard1Balance);
    assertEquals(card2Balance, exactCard2Balance);
  }

  @Test
  void shouldTransferMoneyBetweenOwnCardsV3() {
    var loginPage = open("http://localhost:9999", LoginPageV3.class);
    var authInfo = DataHelper.getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    var dashboardPage = verificationPage.validVerify(verificationCode);
    var card1 = DataHelper.getCardInfo1();
    var card2 = DataHelper.getCardInfo2();
    var card1Balance = dashboardPage.getCardBalance(card1);
    var card2Balance = dashboardPage.getCardBalance(card2);
    var cardPage = dashboardPage.validSelectReceiveCard(card2);
    int transferAmount = 100;
    dashboardPage = cardPage.validTransfer(card1, transferAmount);
    card1Balance -= transferAmount;
    card2Balance += transferAmount;
    var exactCard2Balance = dashboardPage.getCardBalance(card2);
    var exactCard1Balance = dashboardPage.getCardBalance(card1);
    assertEquals(card1Balance, exactCard1Balance);
    assertEquals(card2Balance, exactCard2Balance);
  }
}

