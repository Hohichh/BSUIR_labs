-- -- Задача №30: --
-- SELECT DISTINCT q.product_id
-- FROM quantities q
-- JOIN projects prj ON prj.id = q.project_id
-- WHERE prj.city = 'Лондон';


-- Задача №13: --
-- SELECT DISTINCT q.project_id 
-- FROM quantities q
-- JOIN products prd ON q.product_id = prd.id
-- JOIN projects prj ON q.project_id = prj.id
-- WHERE prd.city != prj.city;

-- Задача №21: --
-- SELECT q.product_id
-- FROM quantities q
-- JOIN projects prj ON prj.id = q.project_id
-- WHERE prj.city = 'Лондон';


--Задача №12: --
-- SELECT q.product_id
-- FROM quantities q
-- JOIN suppliers s ON q.supplier_id = s.id
-- JOIN projects p ON q.project_id = p.id
-- WHERE s.city = p.city;

-- SELECT q.product_id
-- FROM quantities q
-- JOIN suppliers s ON q.supplier_id = s.id
-- JOIN projects p ON q.project_id = p.id
-- WHERE s.city = p.city
-- GROUP BY q.product_id
-- HAVING COUNT(DISTINCT q.project_id) = (
--         SELECT COUNT(DISTINCT p.id)
--         FROM projects p
--         JOIN suppliers s ON s.city = p.city
--         JOIN quantities q ON q.project_id = p.id AND q.supplier_id = s.id
--     );


-- Задача №28: --       
-- SELECT prj.id
-- FROM projects prj
-- WHERE prj.id NOT IN (SELECT q.project_id 
-- FROM quantities q
-- JOIN products prd ON prd.id = q.product_id
-- JOIN suppliers s ON s.id = q.supplier_id
-- WHERE s.city = 'Лондон' AND prd.color = 'Красный');


-- Задача №3: --
-- SELECT q.supplier_id
-- FROM quantities q
-- WHERE q.project_id = 1;

-- Задача №7: --
-- SELECT q.supplier_id, q.product_id, q.project_id
-- FROM quantities q
-- JOIN suppliers s ON s.id = supplier_id
-- JOIN products prd ON prd.id = product_id
-- JOIN projects prj ON prj.id = project_id
-- WHERE s.city != prd.city AND prd.city != prj.city AND s.city != prj.city;


-- Задача №32: --
-- SELECT q.project_id
-- FROM quantities q
-- WHERE q.product_id IN
-- 	(SELECT DISTINCT q.product_id
-- 	FROM quantities q
-- 	WHERE q.supplier_id = 1) 
-- GROUP BY q.project_id
-- HAVING COUNT(DISTINCT q.product_id) = 
-- 	(SELECT COUNT(DISTINCT q.product_id)
-- 	FROM quantities q
-- 	WHERE q.supplier_id = 1);


-- Задача №2: --
-- SELECT p.id AS project_id, p.name AS project_name, p.city AS project_city, 
--        s.name AS supplier_name, s.city AS supplier_city, 
--        pr.name AS product_name, pr.color AS product_color, pr.size AS product_size, 
--        q.quantity
-- FROM projects p
-- JOIN quantities q ON p.id = q.project_id
-- JOIN suppliers s ON q.supplier_id = s.id
-- JOIN products pr ON q.product_id = pr.id
-- WHERE p.city = 'Лондон';


-- Задача №18: --
-- SELECT q.product_id
-- FROM quantities q
-- GROUP BY q.product_id, q.project_id
-- HAVING AVG(q.quantity) > 320;
