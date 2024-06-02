Feature: Cart

  Scenario: Berhasil melihat daftar barang pada halaman Cart ketika terdapat minimal 1 barang
    Given Pengguna berhasil melakukan login ke dalam aplikasi
    And Pengguna berhasil mengakses halaman Dashboard aplikasi
    And Barang sudah ditambahkan ke dalam Cart
    When Pengguna klik gambar Cart pada halaman Dashboard untuk berpindah ke halaman Cart
    Then Sistem menampilkan daftar barang yang berisi:
      | Qty  | Description        | Nama                | Harga Satuan | button Remove |
      | 1    | Sauce Labs Backpack| Sauce Labs Backpack | $29.99       | Remove        |
    And Sistem menampilkan button Continue Shopping
    And Sistem menampilkan button Checkout

  Scenario: Berhasil melihat daftar barang pada halaman Cart ketika Cart kosong
    Given Pengguna berhasil melakukan login ke dalam aplikasi
    And Pengguna berhasil mengakses halaman Dashboard aplikasi
    When Pengguna klik gambar Cart pada halaman Dashboard untuk berpindah ke halaman Cart
    Then Sistem menampilkan daftar Qty dan Description kosong
    And Sistem menampilkan button Continue Shopping
    And Sistem menampilkan button Checkout

  Scenario: Berhasil kembali ke halaman dashboard
    Given Pengguna berhasil melakukan login ke dalam aplikasi
    And Pengguna berhasil mengakses halaman Cart aplikasi
    When Pengguna klik button Continue Shopping
    Then Sistem berpindah ke halaman Dashboard
    And Sistem menampilkan daftar katalog produk