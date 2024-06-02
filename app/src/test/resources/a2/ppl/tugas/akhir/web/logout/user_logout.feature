@logout
Feature: User Logout

    Scenario: Logout Sukses
        Given Pengguna sudah berhasil mengakses website Swaglabs pada browser
        And Pengguna berhasil melakukan login dengan username "standard_user" dan password "secret_sauce"
        And Pengguna berada pada halaman dashboard
        When Pengguna menekan tombol logout
        Then Logout berhasil, sistem menampilkan halaman login