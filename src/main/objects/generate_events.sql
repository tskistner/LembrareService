delimiter //

drop procedure if exists generate_events; //
create procedure generate_events (in id_usuario_p int,
									out ds_exercicio_p text, 
									out ds_resposta_p text)
begin

declare qt_opcao_w int(2);
declare dt_evento_w date;
declare id_expressao_w int(11);
declare id_expressao_2_w int(11);
declare qt_dias_w int(10);
	
	select count(*)
	into   qt_opcao_w
	from   informacao_complementar
	where  id_usuario = id_usuario_p;
	
	if (qt_opcao_w > 0) then
		select floor(rand() * 2) 
		into   qt_opcao_w;
	end if;
	
	if (qt_opcao_w = 0) then /*Buscar valores de eventos pré cadastrados*/
		select STR_TO_DATE(concat(a.ds_valores,'/',year(current_Date)),'%d/%m/%Y'), 
			   concat('Qual data marcante possui a seguinte descrição: "',a.ds_imagem,'" ?'), 
			   c.ds_expressao,
			   c.id_expressao
		into   dt_evento_w, ds_exercicio_p, ds_resposta_p, id_expressao_w
		from   imagem a, exercicio_imagem b, expressao c
		where  a.id_imagem = b.id_imagem 
		and    a.id_expressao = c.id_expressao
		and    b.id_exercicio = 7
		order by rand() limit 1;
		
		/*Opção 1 aleatória*/
		select concat(ds_resposta_p,';;',c.ds_expressao),
			   c.id_expressao
		into   ds_resposta_p, id_expressao_2_w
		from   imagem a, exercicio_imagem b, expressao c
		where  a.id_imagem = b.id_imagem 
		and    a.id_expressao = c.id_expressao
		and    b.id_exercicio = 7
		and    c.id_expressao <> id_expressao_w
		order by rand() limit 1;
		
		/*Opção 2 aleatória*/
		select concat(ds_resposta_p,';;',c.ds_expressao)
		into   ds_resposta_p
		from   imagem a, exercicio_imagem b, expressao c
		where  a.id_imagem = b.id_imagem 
		and    a.id_expressao = c.id_expressao
		and    b.id_exercicio = 7
		and    c.id_expressao not in (id_expressao_w,id_expressao_2_w)
		order by rand() limit 1;
		
	else /*Buscar valores das informações complementares do usuário*/
		select a.nm_pessoa, 
		       b.ds_expressao, 
			   STR_TO_DATE(concat(day(a.dt_nascimento),'/',month(a.dt_nascimento),'/',year(current_Date)),'%d/%m/%Y')
		into   ds_exercicio_p, ds_resposta_p, dt_evento_w
		from   informacao_complementar a, expressao b, usuario c
		where  a.id_usuario = id_usuario_p
		and    a.id_pessoa = b.id_expressao
		and    a.id_usuario = c.id_usuario
		and    b.id_grupo = 2
		order by rand() limit 1;	

		set ds_exercicio_p = concat('Quando é o aniversario de seu/sua ',ds_resposta_p,' ',ds_exercicio_p,'?');
		set ds_resposta_p = DATE_FORMAT(dt_evento_w,'%d/%m/%Y');
			
		/*Opção 1 aleatória : Dia/Mês/Ano*/
		select concat(ds_resposta_p,';;',floor(rand() * 31) + 1,'/',floor(rand() * 12) + 1,'/',year(current_Date))
		into   ds_resposta_p;
		
		/*Opção 2 aleatória : Dia/Mês/Ano*/
		select concat(ds_resposta_p,';;',floor(rand() * 31) + 1,'/',floor(rand() * 12) + 1,'/',year(current_Date))
		into   ds_resposta_p;
		
	end if;
	
	/*Parte 2 - Verificar quantos dias falta/passou do evento*/
	set ds_exercicio_p = concat(ds_exercicio_p,';;','Quantos dias ');
	if (dt_evento_w < current_Date) then
		set ds_exercicio_p = concat(ds_exercicio_p,'passaram desde ',DATE_FORMAT(dt_evento_w,'%d/%m/%Y'),'?');
		set ds_resposta_p = concat(ds_resposta_p,'//',datediff(CURRENT_DATE,dt_evento_w));
	else
		set ds_exercicio_p = concat(ds_exercicio_p,'faltam para ',DATE_FORMAT(dt_evento_w,'%d/%m/%Y'),'?');
		set ds_resposta_p = concat(ds_resposta_p,'//',datediff(dt_evento_w,CURRENT_DATE));
	end if;
	
	/*Quantidade de dias aleatório*/
	select concat(ds_resposta_p,';;',floor(rand() * 365),';;',floor(rand() * 365))
	into ds_resposta_p;
	
   
end; //