package applications;

import org.junit.jupiter.api.Test;

class MenuTest {

  @Test
  void testShowWelcome() {
    Menu.showWelcome();
  }

  @Test
  void testShowCommonMenu() {
    Menu.showCommonMenu();
  }

  @Test
  void testShowStellarSystemMenu() {
    Menu.showStellarSystemMenu();
  }

  @Test
  void testShowAtomMenu() {
    Menu.showAtomMenu();
  }

  @Test
  void testShowSocialNetworkMenu() {
    Menu.showSocialNetworkMenu();
  }

}
