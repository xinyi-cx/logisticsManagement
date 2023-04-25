drop FUNCTION if exists nextvalWithSize;
CREATE FUNCTION nextvalWithSize (v_seq_name VARCHAR(50), v_seq_size int(8)) RETURNS INTEGER
BEGIN
DECLARE current INTEGER;
SET current = 0;
SELECT
    current_val + increment_val * v_seq_size INTO current
FROM
    sequence
WHERE
    seq_name = v_seq_name FOR UPDATE;
UPDATE sequence
SET current_val = current
WHERE
    seq_name = v_seq_name;
RETURN current;
END;
