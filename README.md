# Tugas Kecil 3 Strategi Algoritma
# Penyelesaian Permainan Word Ladder Menggunakan Algoritma UCS, Greedy Best First Search, dan A*

### Dibuat oleh:
| Nama | NIM |
| -------- | --------- |
| Nicholas Reymond Sihite | 13522144 |

## Deskripsi Program
Program ini adalah program untuk menyelesaikan permainan Word Ladder dengan menggunakan 3 algoritma, yaitu Uniform Cost Search, Greedy
Best First Search, dan A-Star Search. Bahasa yang digunakan pada program ini adalah Bahasa Java. Program menerima input 2 kata dalam Bahasa Inggris
dan pilihan algoritma yang ingin digunakan. Jika solusi ditemukan, program akan menampilkan rute, banyak langkah menuju rute, waktu eksekusi,
dan jumlah memori yang digunakan selama pencarian. Jika solusi tidak ditemukan, program akan menampilkan pesan tidak mendapat solusi, waktu eksekusi,
dan jumlah memori yang digunakan selama pencarian. Kamus yang digunakan dalam program ini dicantumkan pada bagian Credits.

## Requirement Program
Disarankan menggunakan Java versi 20 ke atas

## Cara Menggunakan Program
1. Clone repository dengan cara membuka terminal lalu masukkan perintah berikut
   ```sh
   git clone https://github.com/nicholasrs05/Tucil3_13522144.git
   ```
2. Buka folder "src" pada terminal lalu lakukan kompilasi file dengan perintah berikut
   ```sh
   javac Main.java
   ```
3. Jalankan program dengan perintah berikut
   ```sh
   java Main
   ```
4. Masukkan kata asal, kata tujuan, dan algoritma yang ingin digunakan dengan format berikut
    ```sh
    kata_asal
    kata_tujuan
    algoritma
    ```
5. Jika berhasil menemukan solusi, program akan menampilkan rute, banyak langkah, waktu eksekusi (dalam microdetik), dan memori yang digunakan (dalam bytes).
   Jika gagal, program hanya akan menampilkan pesan tidak ada solusi, waktu eksekusi, dan memori yang digunakan.

## Credits
Kamus yang digunakan dalam program ini diambil dari [words_alpha](https://github.com/dwyl/english-words)