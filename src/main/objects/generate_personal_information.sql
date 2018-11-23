delimiter //

drop procedure if exists generate_personal_information; //
create procedure generate_personal_information (in id_usuario_p int,
									out ds_exercicio_p text, 
									out ds_resposta_p text)
begin

declare qt_opcao_w int(2);
declare nm_pessoa_w varchar(255);
declare ds_cidade_atual_w varchar(255);
declare ds_cidade_natal_w varchar(255);
declare ds_endereco_w varchar(255);
declare nr_telefone_w int(10);
declare ds_local_w varchar(255);
declare ds_localizacao_w varchar(255);
declare id_grupo_w int(11);
declare ds_expressao_w varchar(255);
declare id_expressao_w integer;
declare id_expressao2_w integer;

	set id_expressao_w = 0;

	select floor(rand() * 8) + 1
	into   qt_opcao_w
	from   dual;
	
	set qt_opcao_w = 6;
		
	if (qt_opcao_w in (1,2,3,4,5)) then 
		select nm_pessoa, ds_cidade_atual, ds_cidade_natal, ds_endereco, nr_telefone
		into   nm_pessoa_w, ds_cidade_atual_w, ds_cidade_natal_w, ds_endereco_w, nr_telefone_w
		from   usuario
		where  id_usuario = id_usuario_p;
	elseif (qt_opcao_w = 6) then
		select b.ds_expressao, a.nm_pessoa, b.id_expressao
		into   ds_expressao_w, nm_pessoa_w, id_expressao_w
		from   informacao_complementar a, expressao b
		where  a.id_pessoa = b.id_expressao
		and    a.id_usuario = id_usuario_p
		and    b.id_grupo = 2
		order by rand() limit 1;
	else
		select ds_local, ds_localizacao
		into   ds_local_w, ds_localizacao_w
		from   enderecos
		where  id_usuario = id_usuario_p;
	end if;
	
	if (qt_opcao_w = 1) then
		set ds_exercicio_p = 'Qual seu nome?';
		set ds_resposta_p = nm_pessoa_w;
		set ds_expressao_w = nm_pessoa_w;
		set id_grupo_w = 4;			
	elseif (qt_opcao_w = 2) then
		set ds_exercicio_p = 'Qual a cidade em que você mora atualmente?';
		set ds_resposta_p = ds_cidade_atual_w;
		set ds_expressao_w = ds_cidade_atual_w;
		set id_grupo_w = 5;					
	elseif (qt_opcao_w = 3) then
		set ds_exercicio_p = 'Qual a cidade em que você nasceu?';
		set ds_resposta_p = ds_cidade_natal_w;
		set ds_expressao_w = ds_cidade_natal_w;
		set id_grupo_w = 5;	
	elseif (qt_opcao_w = 4) then
		set ds_exercicio_p = 'Qual o seu endereço?';
		set ds_resposta_p = ds_endereco_w;
		set ds_expressao_w = ds_endereco_w;
		set id_grupo_w = 6;	
	elseif (qt_opcao_w = 5) then
		set ds_exercicio_p = 'Qual o seu telefone?';
		set ds_resposta_p = nr_telefone_w;
		set id_grupo_w = 0;
		
		select concat(ds_resposta_p,';;','33',floor(rand() * 1000000),';;33',floor(rand() * 1000000))
		into   ds_resposta_p;
	elseif (qt_opcao_w = 6) then
		set ds_exercicio_p = concat('Quem é ',nm_pessoa_w);
		set ds_resposta_p = ds_expressao_w;
		set id_grupo_w = 2;
	else
		set ds_exercicio_p = concat('Que localização é essa: "',ds_localizacao_w,'"?');
		set ds_resposta_p = ds_local_w;	
		set id_grupo_w = 5;
	end if;
	
	if (id_grupo_w > 0) then
	
		/*Opções aleatórias 1*/
		select concat(ds_resposta_p,';;',ds_expressao), id_expressao
		into   ds_resposta_p, id_expressao2_w
		from   expressao
		where  id_grupo = id_grupo_w
		and    ((id_expressao_w = 0) or (id_expressao <> id_expressao_w))
		order by rand() LIMIT 1;
		
		/*Opções aleatórias 2*/
		select concat(ds_resposta_p,';;',ds_expressao)
		into   ds_resposta_p
		from   expressao
		where  id_grupo = id_grupo_w
		and    ((id_expressao_w = 0) or (id_expressao <> id_expressao_w))
		and    id_expressao <> id_expressao2_w
		order by rand() LIMIT 1;
	
	end if;
   
end; //