Feature: Detail Produk

  Scenario: Berhasil melihat Detail Produk dari barang
    Given Pengguna berhasil login ke dalam aplikasi
    And Pengguna berhasil mengakses halaman Dashboard aplikasi
    When Pengguna klik gambar Produk "Sauce Labs Backpack" pada halaman Dashboard
    Then Sistem menampilkan Gambar, Qty, Description, Nama, Harga Satuan, dan button (Add to cart atau Remove) dari barang pada halaman Detail Produk

  Scenario: Berhasil kembali ke halaman Dashboard dari halaman Detail Produk
    Given Pengguna berhasil login ke dalam aplikasi
    And Pengguna berhasil mengakses halaman Detail Produk aplikasi
    When Pengguna klik Back To Product pada halaman Detail Produk
    Then Sistem berpindah ke halaman Dashboard
    And Sistem menampilkan daftar catalog produk

  Scenario: Berhasil berpindah ke halaman Cart dari halaman Detail Produk
    Given Pengguna berhasil login ke dalam aplikasi
    And Pengguna berhasil mengakses halaman Detail Produk aplikasi
    When Pengguna klik gambar Cart pada halaman Detail Produk
    Then Sistem berpindah ke halaman Cart
    And Sistem menampilkan daftar barang yang telah ditambahkan jika ada atau menampilkan halaman kosong jika tidak terdapat barang