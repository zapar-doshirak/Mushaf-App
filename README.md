# Mushaf App
Mushaf - mobile version of muslims Holy Book - Quran. It helps to read anywhere and anytime.

Book consists of 114 chapters and every chapter consists of ayahs (verses). User can get:
- chapter meta-data
- original arabic text of verse
- translate and explanation
- audio recitation
- ability to choose reciters, translators, fonts, text size and other

# Technologies that where used here:
- Java
- SQLite pre-packaged database
- Room
- MVVM + Android Architecture Components
- Retrofit
- Moshi
- REST API

# Technologies to be used further:
- Glide
- MediaPlayer (or HybridMediaPlayer)
- Thread

I use pre-packaged database with meta-data and original text of verse. All other data I will recieve from this API: https://alquran.cloud/api and load to existing db.
Later, user need be able to take notes on each verse + add images and design card with note. So, there will be menu with 3 items: book(main), setting and notes.
Also, there will be book search and notes search, mode switcher and more.

Design was made in Figma:


