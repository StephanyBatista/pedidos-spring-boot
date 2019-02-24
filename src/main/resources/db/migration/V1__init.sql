
CREATE TABLE [dbo].[categoria](
                                [id] [bigint] Identity(1,1) NOT NULL,
                                [nome] [varchar](255) NOT NULL
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[categoria] ADD PRIMARY KEY CLUSTERED
  (
  [id] ASC
  )
  GO

CREATE TABLE [dbo].[cliente](
                              [id] [bigint] Identity(1,1) NOT NULL,
                              [email] [varchar](255) NOT NULL,
                              [bairro] [varchar](255) NULL,
                              [cep] [varchar](255) NULL,
                              [cidade] [varchar](255) NULL,
                              [estado] [varchar](255) NULL,
                              [rua] [varchar](255) NULL,
                              [nome] [varchar](255) NOT NULL,
                              [senha] [varchar](255) NOT NULL
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[cliente] ADD PRIMARY KEY CLUSTERED
  (
  [id] ASC
  )
  GO

CREATE TABLE [dbo].[produto](
                              [id] [bigint] Identity(1,1) NOT NULL,
                              [descricao] [varchar](255) NULL,
                              [foto] [varchar](255) NULL,
                              [nome] [varchar](255) NOT NULL,
                              [preco] [float] NOT NULL,
                              [quantidade] [int] NOT NULL,
                              [categoria_id] [bigint] NOT NULL
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[produto] ADD PRIMARY KEY CLUSTERED
  (
  [id] ASC
  )
  GO
ALTER TABLE [dbo].[produto]  WITH CHECK ADD  CONSTRAINT [FKopu9jggwnamfv0c8k2ri3kx0a] FOREIGN KEY([categoria_id])
  REFERENCES [dbo].[categoria] ([id])
  GO
ALTER TABLE [dbo].[produto] CHECK CONSTRAINT [FKopu9jggwnamfv0c8k2ri3kx0a]
  GO

CREATE TABLE [dbo].[item_do_pedido](
                                     [id] [bigint] Identity(1,1) NOT NULL,
                                     [quantidade] [int] NOT NULL,
                                     [produto_id] [bigint] NOT NULL
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[item_do_pedido] ADD PRIMARY KEY CLUSTERED
  (
  [id] ASC
  )
  GO
ALTER TABLE [dbo].[item_do_pedido]  WITH CHECK ADD  CONSTRAINT [FKh9wh2ar16h2ss5bj9nvkwewj2] FOREIGN KEY([produto_id])
  REFERENCES [dbo].[produto] ([id])
  GO
ALTER TABLE [dbo].[item_do_pedido] CHECK CONSTRAINT [FKh9wh2ar16h2ss5bj9nvkwewj2]
  GO

CREATE TABLE [dbo].[pedido](
                             [id] [bigint] Identity(1,1) NOT NULL,
                             [sessao] [varchar](255) NOT NULL,
                             [status] [int] NOT NULL,
                             [cliente_id] [bigint] NOT NULL
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[pedido] ADD PRIMARY KEY CLUSTERED
  (
  [id] ASC
  )
  GO
ALTER TABLE [dbo].[pedido]  WITH CHECK ADD  CONSTRAINT [FK30s8j2ktpay6of18lbyqn3632] FOREIGN KEY([cliente_id])
  REFERENCES [dbo].[cliente] ([id])
  GO
ALTER TABLE [dbo].[pedido] CHECK CONSTRAINT [FK30s8j2ktpay6of18lbyqn3632]
  GO

CREATE TABLE [dbo].[pedido_items](
                                   [pedido_id] [bigint] NOT NULL,
                                   [items_id] [bigint] NOT NULL
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[pedido_items] ADD  CONSTRAINT [UK_ne2mqi5c8o19cvexnwxu6ty9k] UNIQUE NONCLUSTERED
(
	[items_id] ASC
)
GO
ALTER TABLE [dbo].[pedido_items]  WITH CHECK ADD  CONSTRAINT [FK7hxo84am57kfw61c8cpltfphs] FOREIGN KEY([items_id])
  REFERENCES [dbo].[item_do_pedido] ([id])
  GO
ALTER TABLE [dbo].[pedido_items] CHECK CONSTRAINT [FK7hxo84am57kfw61c8cpltfphs]
  GO
ALTER TABLE [dbo].[pedido_items]  WITH CHECK ADD  CONSTRAINT [FKma8w8bmgrcrq2g21ib250fb0f] FOREIGN KEY([pedido_id])
  REFERENCES [dbo].[pedido] ([id])
  GO
ALTER TABLE [dbo].[pedido_items] CHECK CONSTRAINT [FKma8w8bmgrcrq2g21ib250fb0f]
  GO

insert into cliente ([email],[bairro],[cep],[cidade],[estado],[rua],[nome],[senha]) values
('joaomaria@gmail.com',	'bairro',	'cep',	'cidade',	'estado', 'teste', 'Teste',	'$2a$10$mSZueAKZXpwyOgtBNdgjxu3N1rFKOfVdXS1sGSp4BqNFHJLRg9sA2');

insert into categoria ([nome]) values
('limpeza');

insert into categoria ([nome]) values
('fruta');

insert into produto ([descricao],[foto],[nome],[preco],[quantidade],[categoria_id]) values
(null, null, 'Veja', 5, 10, 1);

insert into produto ([descricao],[foto],[nome],[preco],[quantidade],[categoria_id]) values
(null, null, 'Pinho', 3, 20, 1);

insert into produto ([descricao],[foto],[nome],[preco],[quantidade],[categoria_id]) values
(null, null, 'Laranja', 1, 100, 2);

