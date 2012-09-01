SELECT k.opis opis, count(r.id_rez) meldunki, sum((r.data_w-r.data_z)*k.cena) zysk 
FROM rezerwacje r 
JOIN pokoje p ON r.id_pokoju=p.id_pokoju  
JOIN klasy k ON p.id_klasy=k.id_klasy
WHERE MONTH(r.data_w)=9 and YEAR(r.data_w)=2012 
GROUP BY k.id_klasy 
ORDER BY k.id_klasy