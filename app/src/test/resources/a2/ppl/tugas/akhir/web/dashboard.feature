@dashboard
Feature: dashboard
    Scenario: verifikasi halaman dashboard menampilkan katalog barang
        Given Sudah login
        And berada pada halaman dashboard
        Then halaman dashboard menampilkan katalog barang
    Scenario: Menambahkan barang ke cart saat belum ada barang yang ditambahkan
        Given Sudah login
        And berada pada halaman dashboard
        When menekan tombol "add to cart" pada card barang "Sauce Lab Backpack"
        Then tombol "add to cart" dari barang "Sauce Lab Backpack" berubah menjadi "remove"
        And jumlah barang pada icon cart menjadi 1
    Scenario: Menambahkan barang ke cart saat sudah ada satu barang yang ditambahkan
        Given Sudah login
        And berada pada halaman dashboard
        And tambah satu barang
        When menekan tombol "add to cart" pada card barang "Sauce Lab Light"
        Then tombol "add to cart" dari barang "Sauce Lab Light" berubah menjadi "remove"
        And jumlah barang pada icon cart menjadi 2