Feature: Menu
    Scenario: Verifikasi aksi menu "All items" dari halaman dashboard akan sistem menampilkan halaman dashboard
        Given Pengguna sudah login
        And Pengguna berada pada halaman dashboard
        When Pengguna membuka menu
        And Pengguna memilih "All items"
        Then Sistem menampilkan halaman dashboard berisi katalog barang
        And Sistem menampilkan icon cart berjumlah 0
    Scenario: Verifikasi aksi menu "Logout" dari halaman detail produk akan keluar dari aplikasi dan sistem menampilkan halaman login
        Given Pengguna sudah login
        And Pengguna berada pada halaman detail produk
        When Pengguna membuka menu
        And Pengguna memilih "Logout"
        Then Sistem keluar dari aplikasi
        And Sistem menampilkan halaman login
    Scenario: Verifikasi aksi menu "About" dari halaman dashboard akan sistem menampilkan halaman pembuat website swag labs
        Given Pengguna sudah login
        And Pengguna berada pada halaman dashboard
        When Pengguna membuka menu
        And Pengguna memilih "About"
        Then Sistem menampilkan halaman pembuat website swag labs
