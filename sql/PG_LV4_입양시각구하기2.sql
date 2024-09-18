-- 코드를 입력하세요
-- 0시 ~ 23시 시간대 생성
WITH RECURSIVE hours AS (
    SELECT 0 AS hour
    UNION ALL
    SELECT hour+1
    FROM hours
    WHERE hour<23
)

-- 시간대별 입양건수 left join
SELECT 
    h.hour AS HOUR,
    COALESCE(COUNT(a.ANIMAL_ID), 0) AS COUNT
FROM hours h LEFT JOIN ANIMAL_OUTS a 
ON h.hour = HOUR(a.datetime)
GROUP BY HOUR
ORDER BY HOUR;