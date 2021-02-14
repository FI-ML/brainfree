# mwe-server

## Requests zum Rumspielen

* GET für den Cart von `thomas.maier`
  * [http://localhost:8087/api/carts/thomas.maier](http://localhost:8087/api/carts/thomas.maier)

* PUT um den Cart von `thomas.maier` zu aktualisieren
  * [http://localhost:8087/api/carts/thomas.maier](http://localhost:8087/api/carts/thomas.maier)
  * Bsp Body:
    ```json
    {
        "id": 1,
        "productIds": [1, 3, 2]
    }
    ```

* GET um alle Produkte nach Name sortiert zu bekommen
  * [http://localhost:8087/api/products](http://localhost:8087/api/products)
    
