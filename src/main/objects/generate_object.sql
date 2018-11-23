delimiter //

drop procedure if exists generate_object; //
create procedure generate_object (out ds_exercicio_p text, out ds_resposta_p text)
begin

declare id_expressao_w integer;

	select concat(b.qt_tamanho,'/',b.ds_valores), b.ds_imagem
	into   ds_exercicio_p, ds_resposta_p
	from   exercicio_imagem a, imagem b
	where  a.id_imagem = b.id_imagem
	and    a.id_exercicio = 3
	order by rand() limit 1;

	/*Opções aleatórias 1*/
	select concat(ds_resposta_p,';;',ds_expressao), id_expressao
	into   ds_resposta_p, id_expressao_w
	from   expressao
	where  id_grupo = 7
	order by rand() limit 1;

	/*Opções aleatórias 2*/
	select concat(ds_resposta_p,';;',ds_expressao)
	into   ds_resposta_p
	from   expressao
	where  id_grupo = 7
	and    id_expressao <> id_expressao_w
	order by rand() limit 1;
   
end; //