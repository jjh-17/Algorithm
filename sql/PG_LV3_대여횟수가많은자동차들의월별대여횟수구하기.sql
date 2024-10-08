SELECT
    MONTH(START_DATE) AS MONTH,
    CAR_ID,
    COUNT(HISTORY_ID) AS RECORDS
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY c
WHERE
    c.START_DATE >= '2022-08-01' AND
    c.START_DATE < '2022-11-01' AND
    CAR_ID in (
        SELECT CAR_ID
        FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
        WHERE
            START_DATE >= '2022-08-01' AND
            START_DATE < '2022-11-01'
        GROUP BY CAR_ID
        HAVING COUNT(HISTORY_ID) >= 5
    )
GROUP BY MONTH, CAR_ID
ORDER BY MONTH ASC, CAR_ID DESC;

# ===================================================

WITH 
FilteredHistory AS (
    SELECT * 
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
    WHERE 
        START_DATE >= '2022-08-01' AND 
        START_DATE < '2022-11-01'
),
EligibleCars AS (
    SELECT CAR_ID
    FROM FilteredHistory
    GROUP BY CAR_ID
    HAVING COUNT(HISTORY_ID) >= 5
)

SELECT 
    MONTH(fh.START_DATE) AS MONTH,
    fh.CAR_ID,
    COUNT(fh.HISTORY_ID) AS RECORDS
FROM FilteredHistory fh
JOIN EligibleCars ec USING(CAR_ID)
GROUP BY MONTH, fh.CAR_ID
ORDER BY MONTH ASC, fh.CAR_ID DESC;