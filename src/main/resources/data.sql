CREATE table Boleto (
    id LONG PRIMARY KEY,
    cliente_id VARCHAR(14),
    valor DECIMAL(10,2),
    valor_pago DECIMAL(10,2),
    data_vencimento DATE,
    data_pagamento DATE,
    status VARCHAR(10)
);

INSERT INTO boleto (id, cliente_id, valor, valor_pago, data_vencimento, data_pagamento, status) VALUES (1, '485.561.292-09', 100.00, 100.00,'2024-01-10', '2024-01-10', 'PAGO');
INSERT INTO boleto (id, cliente_id, valor, valor_pago, data_vencimento, data_pagamento, status) VALUES (2, '308.027.846-84', 150.50, 150.50, '2024-02-15', '2024-02-15', 'PAGO');
INSERT INTO boleto (id, cliente_id, valor, valor_pago, data_vencimento, data_pagamento, status) VALUES (3, '974.485.302-47', 200.25, 200.00, '2024-04-25', NULL, 'PENDENTE');
INSERT INTO boleto (id, cliente_id, valor, valor_pago, data_vencimento, data_pagamento, status) VALUES (4, '702.146.910-81', 75.30, 0.00, '2024-04-20', NULL, 'VENCIDO');
INSERT INTO boleto (id, cliente_id, valor, valor_pago, data_vencimento, data_pagamento, status) VALUES (5, '629.858.403-10', 300.00, 300.00, '2024-05-30', '2024-05-30', 'PAGO');
INSERT INTO boleto (id, cliente_id, valor, valor_pago, data_vencimento, data_pagamento, status) VALUES (6, '518.739.261-20', 50.75, 50.00, '2024-06-05', NULL, 'PENDENTE');
INSERT INTO boleto (id, cliente_id, valor, valor_pago, data_vencimento, data_pagamento, status) VALUES (7, '103.624.587-33', 80.20, 0.00, '2024-07-10', NULL, 'VENCIDO');
INSERT INTO boleto (id, cliente_id, valor, valor_pago, data_vencimento, data_pagamento, status) VALUES (8, '841.927.605-15', 120.00, 120.00, '2024-08-15', '2024-08-15', 'PAGO');
INSERT INTO boleto (id, cliente_id, valor, valor_pago, data_vencimento, data_pagamento, status) VALUES (9, '216.537.894-60', 95.99, 95.99, '2024-09-20', '2024-09-20', 'PAGO');
INSERT INTO boleto (id, cliente_id, valor, valor_pago, data_vencimento, data_pagamento, status) VALUES (10, '375.682.019-07', 200.50, 200.00, '2024-10-25', NULL, 'PENDENTE');
