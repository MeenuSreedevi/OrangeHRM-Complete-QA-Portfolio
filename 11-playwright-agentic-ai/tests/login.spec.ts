import { test, expect } from '../src/fixtures/pages.js';

const validUsername = process.env.ORANGEHRM_USERNAME;
const validPassword = process.env.ORANGEHRM_PASSWORD;

// Every test runs in a new, isolated browser context.
test('ORG-TC-LOGIN-001 @smoke displays login controls', async ({ loginPage }) => {
  await loginPage.goto();
  await expect(loginPage.username).toBeVisible();
  await expect(loginPage.password).toBeVisible();
  await expect(loginPage.submit).toBeEnabled();
});

test('ORG-TC-LOGIN-002, ORG-TC-LOGIN-011 @smoke valid login reaches Dashboard', async ({ loginPage, dashboardPage }) => {
  test.skip(!validUsername || !validPassword, 'Set ORANGEHRM_USERNAME and ORANGEHRM_PASSWORD');
  await loginPage.goto();
  await loginPage.login(validUsername!, validPassword!);
  await dashboardPage.expectLoaded();
});

test('ORG-TC-LOGIN-003 invalid username is rejected', async ({ loginPage }) => {
  test.skip(!validPassword, 'Set ORANGEHRM_PASSWORD');
  await loginPage.goto();
  await loginPage.login('InvalidUser123', validPassword!);
  await expect(loginPage.invalidCredentials).toBeVisible();
});

test('ORG-TC-LOGIN-004, ORG-TC-LOGIN-010 invalid password is rejected', async ({ loginPage }) => {
  test.skip(!validUsername, 'Set ORANGEHRM_USERNAME');
  await loginPage.goto();
  await loginPage.login(validUsername!, 'WrongPassword123');
  await expect(loginPage.invalidCredentials).toBeVisible();
});

test('ORG-TC-LOGIN-005 invalid username and password are rejected', async ({ loginPage }) => {
  await loginPage.goto();
  await loginPage.login('InvalidUser123', 'WrongPassword123');
  await expect(loginPage.invalidCredentials).toBeVisible();
});

test('ORG-TC-LOGIN-006 @smoke blank username requires validation', async ({ loginPage }) => {
  await loginPage.goto();
  await loginPage.login('', 'sample-password');
  await expect(loginPage.requiredMessages).toHaveCount(1);
});

test('ORG-TC-LOGIN-007 @smoke blank password requires validation', async ({ loginPage }) => {
  await loginPage.goto();
  await loginPage.login('Admin', '');
  await expect(loginPage.requiredMessages).toHaveCount(1);
});

test('ORG-TC-LOGIN-008 @smoke both fields blank require validation', async ({ loginPage }) => {
  await loginPage.goto();
  await loginPage.submit.click();
  await expect(loginPage.requiredMessages).toHaveCount(2);
});

test('ORG-TC-LOGIN-009 password input is masked', async ({ loginPage }) => {
  await loginPage.goto();
  await expect(loginPage.password).toHaveAttribute('type', 'password');
});

test('ORG-TC-LOGIN-012 @smoke forgot password link exists', async ({ loginPage }) => {
  await loginPage.goto();
  await expect(loginPage.forgotPassword).toBeVisible();
});

test('ORG-TC-LOGIN-013 @smoke forgot password link navigates', async ({ loginPage, page }) => {
  await loginPage.goto();
  await loginPage.forgotPassword.click();
  await expect(page).toHaveURL(/\/auth\/requestPasswordResetCode/);
});

test('ORG-TC-LOGIN-015 @smoke logout returns to login page', async ({ loginPage, dashboardPage }) => {
  test.skip(!validUsername || !validPassword, 'Set ORANGEHRM_USERNAME and ORANGEHRM_PASSWORD');
  await loginPage.goto();
  await loginPage.login(validUsername!, validPassword!);
  await dashboardPage.expectLoaded();
  await dashboardPage.logout();
});
