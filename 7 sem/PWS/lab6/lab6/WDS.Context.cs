﻿//------------------------------------------------------------------------------
// <auto-generated>
//     Этот код создан по шаблону.
//
//     Изменения, вносимые в этот файл вручную, могут привести к непредвиденной работе приложения.
//     Изменения, вносимые в этот файл вручную, будут перезаписаны при повторном создании кода.
// </auto-generated>
//------------------------------------------------------------------------------

namespace lab6
{
    using System.Data.Entity;
    using System.Data.Entity.Infrastructure;
    
    public partial class WDSEntities : DbContext
    {
        public WDSEntities()
            : base("name=WDSEntities")
        {
        }
    
        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            throw new UnintentionalCodeFirstException();
        }
    
        public virtual DbSet<note> note { get; set; }
        public virtual DbSet<student> student { get; set; }
    }
}
