@Login
Feature: User Login

  Scenario: Login dengan username dan password yang valid
    Given Pengguna berhasil mengakses website Swaglabs pada browser
    And Pengguna berada pada halaman login
    When Pengguna menginput username "standard_user" dan password "secret_sauce"
    And Pengguna menekan tombol login
    Then Login berhasil dan sistem menampilkan halaman dashboard yang berisi daftar katalog produk

  Scenario: Login hanya dengan mengisi username
    Given Pengguna berhasil mengakses website Swaglabs pada browser
    And Pengguna berada pada halaman login
    When Pengguna menginput username "standard_user"
    And Pengguna menekan tombol login
    Then Login gagal, current screen tetap berada pada halaman login, dan sistem menampilkan pesan error "You need Password!"

  Scenario: Login dengan username yang tidak valid dan password yang valid
    Given Pengguna berhasil mengakses website Swaglabs pada browser
    And Pengguna berada pada halaman login
    When Pengguna menginput username "test123456" dan password "secret_sauce"
    And Pengguna menekan tombol login
    Then Login gagal, current screen tetap berada pada halaman login, dan sistem menampilkan pesan error "Error Username doesn't match"

  Scenario: Login dengan username dan password yang tidak valid
    Given Pengguna berhasil mengakses website Swaglabs pada browser
    And Pengguna berada pada halaman login
    When Pengguna menginput username "test123456" dan password "test123456"
    And Pengguna menekan tombol login
    Then Login gagal, current screen tetap berada pada halaman login, dan sistem menampilkan pesan error "Username and password do not match any user in this service!"

  Scenario: Login dengan tidak mengisi username dan password
    Given Pengguna berhasil mengakses website Swaglabs pada browser
    And Pengguna berada pada halaman login
    And Pengguna menekan tombol login
    Then Login gagal, current screen tetap berada pada halaman login, dan sistem menampilkan pesan error "You need Username & Password!"
  