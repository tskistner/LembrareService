delimiter //

drop procedure if exists generate_chart; //
create procedure generate_chart (id_usuario_p integer, id_categoria_p integer, dt_inicio_p date, dt_fim_p date)

begin

	declare done int default false;
	declare qt_atual_w integer;
	declare qt_anterior_w integer;
	declare dt_atualizacao_w date;
    declare c01 cursor 
		for select (select qt_nivel from historico_level b where b.id_historico = ifnull(max(a.id_historico),0)) qt, dt
			from (select DISTINCT date(a.dt_atualizacao) dt 
				  from   historico_level a 
				  where  a.id_usuario = id_usuario_p
				  and    a.dt_atualizacao between dt_inicio_p and dt_fim_p) x 
				left join historico_level a on x.dt = date(a.dt_atualizacao) and a.id_usuario = id_usuario_p and a.id_categoria = id_categoria_p
			group by x.dt
			order by x.dt desc;
	declare continue HANDLER for not found set done = true;
	
	
	select ifnull(max(qt_nivel),0) 
	into   qt_anterior_w
	from   historico_level
	where  id_historico = (	select max(id_historico) from historico_level 
							where  id_categoria = id_categoria_p and date(dt_atualizacao) < dt_inicio_p); /*STR_TO_DATE('2018/10/01','%Y/%m/%d')*/
	
	delete from w_Chart;

	open c01;
	SOURCE : loop
	fetch c01 
	into qt_atual_w, dt_atualizacao_w;
		if done then
			leave SOURCE;
		end if;
		
		if (qt_atual_w is null) then
			set qt_atual_w := qt_anterior_w;
		else
			set qt_anterior_w := qt_atual_w;
		end if;
		
		insert into w_Chart values (qt_atual_w, dt_atualizacao_w);
		
	end loop;
	close c01;
	
	commit;
	

end; //