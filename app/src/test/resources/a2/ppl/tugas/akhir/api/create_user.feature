Feature:  Create User
    Scenario: Operasi tanpa menambahkan app-id pada header
        When Menghapus header request "app-id"
        And Mengirim request ke "https://dummyapi.io/data/v1/user/create" dengan method POST
        Then Menerima status code 403
        And Menerima response body json dengan key "error" value "APP_ID_MISSING"