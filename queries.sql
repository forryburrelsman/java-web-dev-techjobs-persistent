## Part 1: Test it with SQL
SELECT * FROM techjobs.job;

## Part 2: Test it with SQL
SELECT *
FROM techjobs.employer
WHERE location = "St. Louis";

## Part 3: Test it with SQL
DROP TABLE IF EXISTS techjobs.job;

## Part 4: Test it with SQL
SELECT skill.name, description
FROM skill
INNER JOIN job_skills ON skill.id = job_skills.skills_id
WHERE jobs_id is not null;