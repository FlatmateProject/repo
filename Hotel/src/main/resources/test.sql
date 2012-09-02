SELECT u.typ, sum(rk.czas) czas, sum(rk.czas*u.cena) zysk 
FROM rekreacja rk 
JOIN uslugi u ON rk.id_uslugi=u.id_uslugi 
JOIN rezerwacje rz ON rk.id_rez=rz.id_rez 
WHERE MONTH(rz.data_w)=9 and YEAR(rz.data_w)=2012 
GROUP BY u.typ