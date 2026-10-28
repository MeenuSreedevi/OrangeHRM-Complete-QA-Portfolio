import { expect, type Page } from '@playwright/test';

export class DashboardPage {
  constructor(readonly page: Page) {}
  async expectLoaded() {
    await expect(this.page).toHaveURL(/\/web\/index\.php\/dashboard\//);
    await expect(this.page.getByRole('heading', { name: 'Dashboard' })).toBeVisible();
  }
  async logout() {
    await this.page.locator('.oxd-userdropdown-tab').click();
    await this.page.getByRole('menuitem', { name: 'Logout' }).click();
    await expect(this.page).toHaveURL(/\/auth\/login/);
  }
}
