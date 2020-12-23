## Pokepipline File Structure
------

```
📦com.revature.pokepipeline
 ┣ 📂controller
 ┣ 📂dao
 ┃ ┗ 📂impl
 ┣ 📂gamelogic
 ┣ 📂model
 ┃ ┣ 📂Item
 ┃ ┃ ┣ 📜int itemAPI
 ┃ ┃ ┗ 📜int itemId
 ┃ ┣ 📂Move
 ┃ ┃ ┣ 📜boolean physical
 ┃ ┃ ┣ 📜int power
 ┃ ┃ ┣ 📜String name
 ┃ ┃ ┗ 📜Type(enum) type
 ┃ ┣ 📂Pokemon
 ┃ ┃ ┣ 📜double currentHP
 ┃ ┃ ┣ 📜int exp
 ┃ ┃ ┣ 📜int pokemonAPI
 ┃ ┃ ┣ 📜int pokemonId
 ┃ ┃ ┗ 📜List moveList
 ┃ ┗ 📂User
 ┃ ┃ ┣ 📜int userId
 ┃ ┃ ┣ 📜List itemList
 ┃ ┃ ┣ 📜pokemonList
 ┃ ┃ ┗ 📜String username
 ┣ 📂service
 ┃ ┣ 📂impl
 ┃ ┗ 📂util
 ┗ 📂servlet
```
------
