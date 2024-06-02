Feature: Update User
TC3-10
TC3-23
TC3-14
    Scenario: Operasi menambahkan app-id yang tidak valid pada header
        When Mengisi app-id pada header dengan id yang tidak valid "abc"
        And Mengirim request ke "https://dummyapi.io/data/v1/user/60d0fe4f5311236168a109cc" dengan method Update
        Then Status Response: 403 "Forbidden"
        And Response body: "APP_ID_MISSING"
    Scenario: Mengubah value dari field lastName dengan 8 karakter
        Given Menambahkan app-id yang valid pada header request "6633dd597309359fe9ceb8ad"
        When Mengisi request body dengan
        """
        {
        "lastName" : "Iskandar"
        }
        """
        And Mengirim request ke "https://dummyapi.io/data/v1/user/60d0fe4f5311236168a109cc" dengan method Update
        Then Status Response: 200 "OK"
        And Menerima response body JSON User yang telah diubah
    Scenario: Mengubah value dari field title dengan isi "miss"
        Given Menambahkan app-id yang valid pada header request "6633dd597309359fe9ceb8ad"
        When Me