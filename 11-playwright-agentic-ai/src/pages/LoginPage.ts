import { expect, type Locator, type Page } from '@playwright/test';

export class LoginPage {
  readonly username: Locator;
  readonly password: Locator;
  readonly submit: Locator;
  readonly invalidCredentials: Locator;
  readonly requiredMessages: Locator;
  readonly forgotPassword: Locator;

  constructor(readonly page: Page) {
    this.username = page.getByPlaceholder('Username', { exact: true });
    this.password = page.getByPlaceholder('Password', { exact: true });
    this.submit = page.getByRole('button', { name: 'Login', exact: true });
    this.invalidCredentials = page.getByText('Invalid credentials', { exact: true });
    this.requiredMessages = page.getByText('Required', { exact: true });
    this.forgotPassword = page.getByText('Forgot your password?', { exact: true });
  }

  async goto() {
    await this.page.goto('/web/index.php/auth/login');
    await expect(this.submit).toBeVisible();
  }
  async login(username: string, password: string) {
    await this.username.fill(username);
    await this.password.fill(password);
    await this.submit.click();
  }
}
