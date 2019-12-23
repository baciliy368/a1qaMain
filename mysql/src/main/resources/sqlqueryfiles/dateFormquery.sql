SELECT id.name as project, test.name as testName, test.end_time as endTime FROM test INNER JOIN project AS id ON test.project_id = id.id WHERE end_time > %s ORDER BY id.name ASC, test.name ASC;