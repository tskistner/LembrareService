delimiter //

drop procedure if exists generate_image; //
create procedure generate_image (out ds_exercicio_p text, out ds_resposta_p text, out ds_imagem_p blob, out ds_sound_p blob)
begin


	select b.ds_imagem, ds_valores, b.b_imagem, b.ds_som
	into   ds_exercicio_p, ds_resposta_p, ds_imagem_p, ds_sound_p
	from   exercicio_imagem a, imagem b
	where  a.id_imagem = b.id_imagem
	and    a.id_exercicio = 5
	order by rand() limit 1;
	
	/*Opção aleatória 1*/
	select concat(ds_resposta_p,';;',ds_expressao)
	into   ds_resposta_p
	from   expressao
	order by rand() limit 1;
	
	/*Opção aleatória 2*/
	select concat(ds_resposta_p,';;',ds_expressao)
	into   ds_resposta_p
	from   expressao
	order by rand() limit 1;
   
end; //